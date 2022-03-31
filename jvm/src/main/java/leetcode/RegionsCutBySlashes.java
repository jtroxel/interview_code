package leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        Map<Point, Node> nodesByValue = new HashMap<>();
        // Build a Graph for the bounding edges
        int boxSize = grid.length;
        if (boxSize == 0) return 0;
        buildGraph(grid, nodesByValue, boxSize);
        return 1;
    }

    private void buildGraph(String[] grid, Map<Point, Node> nodesByValue, int boxSize) {
        Node root, lastVertBoundary, lastHorizBoundary;
        root = lastVertBoundary = lastHorizBoundary = new Node(0, 0);

        // Parse the input into a graph
        int maxCellIndex = boxSize - 1;
        for (int y = boxSize; y >= 1; y--) {
            for (int x = 1; x <= boxSize; x++) {
                // read next cell @ x, y
                Cell cell = new Cell(grid[boxSize-y].charAt(x-1));
                // If part of an edge, add edge
                if (cell.isSlash()) {
                    // Ex: '/' at 2,2 is a (partial) edge between 3,3 and 1,1
                    Point[] diffPoints = cell.getXandYdiffs();
                    // TODO find for Point?
                    Point topCorner = getCornerPoint(x, y, diffPoints[0]);
                    Point bottomCorner = getCornerPoint(x, y, diffPoints[1]);
                    findOrAddNodes(nodesByValue, topCorner, bottomCorner, root);
                    // If on the boundary, connect to previous node
                    if (topCorner.isVertBoundary(maxCellIndex)) {
                        Point newBoundary = new Point(0, y);
                        lastVertBoundary = findOrAddNodes(nodesByValue, newBoundary, lastVertBoundary.value, root);
                    }
                    if (topCorner.isHorizBoundary(maxCellIndex)) {
                        Point newBoundary = new Point(x, 0);
                        lastHorizBoundary = findOrAddNodes(nodesByValue, newBoundary, lastHorizBoundary.value, root);
                    }
                }
            }
        }
    }

    @NotNull
    private Point getCornerPoint(int x, int y, Point xandYdiffs) {
        return new Point(x + xandYdiffs.x, y + xandYdiffs.y);
    }

    private Node findOrAddNodes(Map<Point, Node> nodesByValue, Point topCorner, Point botCorner, Node root) {
        Node to = findOrAddNode(nodesByValue, botCorner);
        Node from = findOrAddNode(nodesByValue, topCorner);
        if (nodesByValue.containsKey(topCorner)) {
            to.setPredecessor(from);
            from.addAdjacent(to);
        } else if (nodesByValue.containsKey(botCorner)) {
            from.setPredecessor(to);
        } else {
            from.setPredecessor(root);
            to.addAdjacent(from);
        }
        return to;
    }

    private Node findOrAddNode(Map<Point, Node> nodesByValue, Point point) {
        Node newNode;
        if (nodesByValue.containsKey(point)) {
            newNode = nodesByValue.get(point); //
        } else {
            newNode = new Node(point);
            nodesByValue.put(point, newNode);
        }
        return newNode;
    }

    private class Graph {
        Node root;

        public Graph(Node root) {
            this.root = root;
        }
    }

    private class Node {

        private Point value;

        private Node predecessor;
        private List<Node> adjacents = new ArrayList<>();

        public Node(int x, int y) {
            this.value = new Point(x,y);
        }

        public Node(Point value) {
            this.value = value;
        }

        public Node(Node predecessor, Point value) {
            this.value = value;
            this.predecessor = predecessor;
        }

        public Node(Node predecessor, int x, int y) {
            this(x, y);
            this.predecessor = predecessor;
        }

        public Node getPredecessor() {
            return predecessor;
        }

        public void setPredecessor(Node predecessor) {
            this.predecessor = predecessor;
        }

        public Node addAdjacent(Node n) {
            this.adjacents.add(n);
            return this;
        }

        public List<Node> getAdjacents() {
            return adjacents;
        }
    }

    private class Cell {
        private char content;
        public Cell(char charAt) {
            this.content = charAt;
        }

        public boolean isSlash() {
            return (this.content == '/' || this.content == '\\');
        }


        public Point[] getXandYdiffs() {
            if (this.content == '/') {
                return new Point[]{new Point(0, 0), new Point(-1, -1)};
            } else if (this.content == '\\') {
                return new Point[]{new Point(0, 0), new Point(-1, -1)};
            }
            return null;
        }

    }

    private class Point extends Object{
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(@NotNull Point other) {
            return (this.x == other.x && this.y == other.y);
        }

        public String toString() {
            return "[" + x + ", " + y +"]";
        }

        public boolean isVertBoundary(int max) {
            return (this.x == 0 || this.x == max);
        }

        public boolean isHorizBoundary(int max) {
            return (this.y == 0 || this.y == max);
        }
    }
}

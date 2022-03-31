package icamp;

import javax.swing.*;
import java.util.*;

class Graph {
    private List<Node> roots;
    private Set<Integer> searchVisited;

    /**
     * Simple constructor, creates from a Map like:
     * 1->2,3; 2->4; 3->4,5; 4->6; 5->6
     */
    public Graph(Map<Integer, List<Integer>> graphSpec) {
        roots = new ArrayList<>();
        Map<Integer, Node> allNodes = new HashMap<>();
        // - Loop through keys:
        for (Map.Entry<Integer, List<Integer>> mapping : graphSpec.entrySet()) {
            Integer key = mapping.getKey();
            Node newNode = getOrCreateNode(key, allNodes, true);
            // Add all edges from here
            for (Integer toKey : mapping.getValue()) {
                newNode.addAdjacent(getOrCreateNode(toKey, allNodes, false));
            }
        }
    }

    public List<Node> getRoots() {
        return roots;
    }

    private Node getOrCreateNode(Integer key, Map<Integer, Node> allNodes, boolean addRoot) {
        Node newNode;
        if (allNodes.containsKey(key)) {
            newNode = allNodes.get(key);
        } else {
            newNode = new Node(key);
            allNodes.put(key, newNode);
            if (addRoot) {
                roots.add(newNode);
            }
        }
        return newNode;
    }

    public boolean dfsExists(Integer key) {
        this.searchVisited = new HashSet<>();
        //Search all roots
        for (Node root : this.roots) {
            if (dfs(root, key, new NodeVisitor<Boolean>() {
                @Override
                public Boolean visit(Node node) {
                    return (Boolean) (node.value == key);
                }
            })) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Node node, Integer key, NodeVisitor visitor) {
        if ((Boolean) node.visit(visitor)) {
            trackVisited(node);
            return true;
        }
        for (Node adjacent : node.getAdjacents()) {
            if (!isVisited(adjacent)) {
                if (dfs(adjacent, key, visitor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isVisited(Node adjacent) {
        return this.searchVisited.contains(adjacent.value);
    }

    private boolean trackVisited(Node node) {
        return this.searchVisited.add(node.value);
    }

    public static class Node extends Object {
        public int value;
        private List<Node> adjacents;

        public Node(int val) {
            this.value = val;
            this.adjacents = new ArrayList<>();
        }

        public Node addAdjacent(Node adj) {
            this.adjacents.add(adj);
            return this;
        }

        public List<Node> getAdjacents() {
            return adjacents;
        }

        public Object visit(NodeVisitor visitor) {
            return visitor.visit(this);
        }

        public String toString() {
            return this.value + " -> " + this.adjacents;
        }

    }

    public interface NodeVisitor<E> {
        E visit(Node node);
    }
}

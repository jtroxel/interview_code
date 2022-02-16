package icamp;

public class IslandMatrix {
    private int[][] matrix;
    public IslandMatrix(final int[][] matrix) {
        this.matrix = matrix;
    }

    // TODO:  redo with brute force
    public int calcIslands() {
        int[] start = {0, 0};
        // visit first
        return 0; // TODO
    }

    private int visitIsland(int[] pos, boolean neighborIsIsland, int count) {
        if (pos[1] == -1) {
            return -1;
        }

        // If I am an island:
        //  - count east, visit south
        return 0;
//        if (!neighborIsIsland && getIslandAt(pos) == 1) {
//            return 1 + visitIsland(nextPos(pos), true);
//        } else {
//            return visitIsland(nextPos(pos), neighborIsIsland);
//        }
    }

    private int[] nextPos(int[] pos) {
        int y = yPos(pos);
        int x = xPos(pos);
        x = rollingIncrement(x, this.matrix[y]);
        if (x == 0) { // Rolled over rows
            y = rollingIncrement(y, this.matrix[0]);
            if (y == 0) { // Rolled over columns
                y = -1; // mark as done.
            }
        }
        pos[0] = x;
        pos[1] = y;
        return pos;
    }

    private int rollingIncrement(int idx, int[] dimension) {
        return idx % dimension.length;
    }

    private int xPos(int[] pos) {
        return pos[0];
    }

    private int yPos(int[] pos) {
        return pos[0];
    }

    private int getIslandAt(int[] pos) {
        return this.matrix[xPos(pos)][yPos(pos)];
    }
}

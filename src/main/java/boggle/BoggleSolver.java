package boggle; /**
 * User: jtroxel
 * Date: 2/24/17
 * Time: 2:31 PM
 */

import java.util.HashSet;
import java.util.Set;

/**
 * BoggleSolver: Solver class for finding word in a letter grid
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class BoggleSolver {

    // Internal interface for implementing a recursive function call
    interface CellVisitor {
        void visited(Set allWords, int y, int x, Set visited, String thisWord);
    }

    String[][] grid;
    BoggleDictionary dictionary;
    Set allWords;

    public BoggleSolver(String[][] grid, BoggleDictionary dict) {
        this.grid = grid;
        this.dictionary = dict;
    }

    public Set allPossibleWords() {

        allWords = new HashSet();
        // Build adjacency list
        // for each cell in board.getGrid(), add adjacent cells
        //   previous row same index, i-1, i+1, if exist
        //   this row i-1, i+1, if exist
        //   next row same index, i-1, i+1, if exist

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                String thisWord = "";
                Set visited = new HashSet();
                doCell(allWords, i, j, visited, thisWord);
            }

        }
        return allWords;
    }

    protected void doCell(Set allWords, int y, int x, Set visited, String thisWord) {
        String candidate = thisWord + grid[y][x];
        boolean isDictWord = this.dictionary.isWord(candidate);
        if (isDictWord) {
            allWords.add(candidate);
        }
        // Bail out if this is not a word or the root of another word
        if (!(isDictWord || this.dictionary.isWordRoot(candidate))) {
            return;
        }
        visited.add(visitedPos(x, y));
//        System.out.println(candidate);
        // Visit all adjacent cells that are on the board and not visited, recursively process those
        visitValidAdjacents(y, x, candidate, visited, this::doCell);
    }

    private void visitValidAdjacents(int gridY, int gridX, String thisWord, Set visited,
                                     CellVisitor visitBlock) {
        // Check all adjacent cells for an extension (unless off board or already visited)
        for (int yAdjust = -1; yAdjust <= 1; yAdjust++) {
            for (int xAdjust = -1; xAdjust <= 1; xAdjust++) {

                if (validCheck(gridY, gridX, yAdjust, xAdjust, visited)) {
                    visitBlock.visited(allWords, gridY + yAdjust, gridX + xAdjust, new HashSet(visited), thisWord);
                }
            }

        }
    }

    // Check if cell is on board and not already visited
    private boolean validCheck(int gridY, int gridX, int yAdjust, int xAdjust, Set visited) {
        int checkX, checkY;
        checkX = gridX + xAdjust;
        checkY = gridY + yAdjust;
        return !(checkX < 0 || checkX >= this.grid.length || checkY < 0 || checkY >= this.grid.length || visited.contains(visitedPos(checkX, checkY)));
    }

    private int visitedPos(int checkX, int checkY) {
        return (checkY * this.grid.length) + checkX;
    }

}

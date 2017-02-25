package boggle; /**
 * User: jtroxel
 * Date: 2/24/17
 * Time: 2:15 PM
 */

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * boggle.BoggleTest: TODO
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class BoggleTest {

    @Test
    public void solveSimple() {
        String [][] testGrid = new String [][] {
                { "G", "O", "D" },
                { "O", "F", "S" },
                { "D", "O", "G" }
        };
//        boggle.BoggleBoard board = new boggle.BoggleBoard(testGrid);
        BoggleSolver solver = new BoggleSolver(testGrid, new BoggleDictionary() {
            @Override
            public boolean isWord(String word) {
                switch (word) {
                    case "GO":
                    case "GOD":
                    case "GOOD":
                    case "FOOD":
                    case "DOGFOOD":
                    case "DOG":
                        return true;
                    default:
                          return false;
                }
            }

            @Override
            public boolean isWordRoot(String currentWord) {
                return true;
            }
        });
        Set<String> allWords = solver.allPossibleWords();

        assert(allWords.contains("GO"));
        assert(allWords.contains("GOD"));
        assert(allWords.contains("DOGFOOD"));
        assert(!allWords.contains("FOO"));
        assertEquals(6, allWords.size());
    }
    @Test
    public void solveRealDictionary() {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[][] testGrid = new String[][]{
                {"G", "O", "D", "O"},
                {"O", "F", "S", "F"},
                {"D", "O", "G", "F"},
                {"N", "A", "I", "L"}
        };
//        boggle.BoggleBoard board = new boggle.BoggleBoard(testGrid);
        BoggleSolver solver = new BoggleSolver(testGrid, new BoggleHashMapDictionary(bReader, true));
        Set<String> allWords = solver.allPossibleWords();

        System.out.println(allWords);

        assert (!allWords.contains("GO")); // Must be 3 letters
        assert (allWords.contains("GOD"));
        assert (!allWords.contains("GOOFOFF")); // In dict, but uses visited letters
        assert (allWords.contains("OSGOOD"));
        assert (!allWords.contains("OFS")); // Just not a word :)
        assert (allWords.contains("NAIL"));
        assert (allWords.contains("GODSON"));
        assert (!allWords.contains("FO"));
        assertEquals(75, allWords.size());
    }
}

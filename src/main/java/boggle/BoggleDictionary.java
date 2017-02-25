package boggle; /**
 * User: jtroxel
 * Date: 2/24/17
 * Time: 2:59 PM
 */

/**
 * BoggleDictionary: Interface for methods to look up boggle words
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public interface BoggleDictionary {
    boolean isWord(String word);
    boolean isWordRoot(String currentWord);
}


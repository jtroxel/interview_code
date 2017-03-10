import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: jtroxel
 * Date: 3/9/17
 * Time: 4:41 PM
 */
public class SlugGeneratorTest {

    SlugGenerator slugger;

    @Before
    public void setSlugger() {
        slugger = new SlugGenerator();
    }

    @Test
    public void caseLTEDigitNumbers() {

        String slug = slugger.generate(0);
        assertEquals("0", slug);
        slug = slugger.generate(9);
        assertEquals("9", slug);
        slug = slugger.generate(10);
        assertNotEquals("10", slug);
    }
    @Test
    public void caseLTELetters() {

        String slug = slugger.generate(10);
        assertEquals("a", slug);
        slug = slugger.generate(35);
        assertEquals("z", slug);
        slug = slugger.generate(36);
        assertNotEquals("z", slug);
    }

    @Test
    public void caseTwoDigits() {
        String slug = slugger.generate(36);
        assertEquals("10", slug);
    }
}
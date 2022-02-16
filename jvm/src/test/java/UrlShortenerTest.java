/**
 * User: jtroxel
 * Date: 3/2/17
 * Time: 4:35 PM
 */

import io.kotest.core.spec.style.AnnotationSpec;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * UrlShortenerTest: TODO
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class UrlShortenerTest {

    UrlShortener shortener;

    UrlShortener.LinkStore testMapStore = new UrlShortener.LinkStore() {

        Map<String, UrlShortener.Link> slugToUrl = new HashMap<>();
        Map<String, UrlShortener.Link> urlToSlug = new HashMap<>();

        @Override
        public UrlShortener.Link find(String slug, String url) {
            if (slug != null) {
                return slugToUrl.get(slug);
            } else if (url != null) {
                return urlToSlug.get(url);
            } else {
                return null;
            }
        }

        @Override
        public UrlShortener.Link add(String slug, String url) {
            UrlShortener.Link newLink = new UrlShortener.Link(slug, url);
            slugToUrl.put(slug, newLink);
            urlToSlug.put(url, newLink);
            return newLink;
        }
    };

    @AnnotationSpec.Before
    public void setupShortener() {
        shortener = new UrlShortener(testMapStore);
    }

    @Test
    public void uniqueSlug() throws Exception {
        Set<String> generated = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String newSlug = shortener.generateSlug();
            System.out.println(newSlug);
            assert(!generated.contains(newSlug));
            generated.add(newSlug);
        }
    }
}

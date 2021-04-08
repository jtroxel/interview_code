/**
 * User: jtroxel
 * Date: 3/2/17
 * Time: 4:37 PM
 */

import java.util.Date;

/**
 * UrlShortener: TODO
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class UrlShortener {

    private LinkStore store;
    private long linkCounter = 1;

    public static class Link {
        private String slug;
        private String url;

        public Link(String slug, String url) {
            this.slug = slug;
            this.url = url;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public interface LinkStore {
        Link find(String slug, String url);
        Link add(String slug, String url);
    }

    public UrlShortener(LinkStore store) {
        this.store = store;
    }

    public String add(String slug, String url) throws Exception {
        if (store.find(slug, null) != null) {
            throw new Exception("already have this slug");
        }
        Link added = store.add(slug, url);
        return added.getSlug();
    }

    public String resolve(String slug) throws Exception {
        Link found = findSlug(slug);
        if (found == null) {
            throw new Exception("can't resolve this slug");
        }
        return found.getUrl();
    }

    private Link findSlug(String slug) {
        return store.find(slug, null);
    }

    public String generateSlug() throws Exception {
        long timestamp;
        String candidateSlug;
        do {
            timestamp = new Date().getTime();
            candidateSlug = Integer.toHexString((int) (timestamp));
        } while (store.find(candidateSlug, null) != null);
        store.add(candidateSlug, "");
        return candidateSlug;
    }

    /**
     * UrlShortener.SlugGenerator: TODO
     *
     * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
     */
    public static class SlugGenerator {

        private int base = 36; // [0-9][a-z]

        public String generate(int id) {

            if (id >= base) {
                return generate(id / base) + generate(id % base);
            }

            if (id <= 9) {
                return "" + id;
            } else if (id >= 10) {
                return "" + (char)('a' + (id-10));
            }

            return "0";
        }
    }
}

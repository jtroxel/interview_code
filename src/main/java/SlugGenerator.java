/**
 * User: jtroxel
 * Date: 3/9/17
 * Time: 4:27 PM
 */

/**
 * SlugGenerator: TODO
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class SlugGenerator {

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

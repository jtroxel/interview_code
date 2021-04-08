/**
 * Created by IntelliJ IDEA.
 * User: jtroxel
 * Date: May 31, 2011
 * Time: 2:12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class CharDedupe {

    public static void main(String[] args) {
        CharDedupe self = new CharDedupe();
        System.out.println(self.dedupeString("aaabbbcccxxxaaa"));
        System.out.println(self.dedupeString("aaaax"));
        System.out.println(self.dedupeString("a"));
    }

    private String dedupeString(String input) {
        char match = ' ';
        String ret = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != match) {
                ret += input.charAt(i);
                match = input.charAt(i);
            }
        }
        return ret;
    }
}

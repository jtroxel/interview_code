import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ArrayProd: Takes a list of integer values from stdin, produces a list of products of all elements excluding the ordinal
 *    [2, 3,4,5]
 *      =>
 *    [60, 40, 30, 24]
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class ArrayProd {
    public static void main(String[] args) throws IOException {
        final Pattern p = Pattern.compile("\\d+");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = in.readLine()) != null) {
            Matcher m = p.matcher(input);
            List<String> asStrings = new ArrayList<>();
            while(m.find()) {
                asStrings.add(m.group());
            }
            List<Integer> inputArr = asStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
            System.out.println(Arrays.toString(get_products_of_all_ints_except_at_index(inputArr)));
        }
    }

    private static Integer[] get_products_of_all_ints_except_at_index(List<Integer> inputVals) {
        List<Integer> products = new ArrayList<>();
        for (Integer curr : inputVals) {
            products.add(inputVals.stream().filter(x -> !x.equals(curr)).reduce(1, (x, y) -> x * y));
        }
        return (Integer[]) products.toArray(new Integer[] {});
    }
}


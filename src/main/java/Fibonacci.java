/**
 * User: jtroxel
 * Date: 8/21/15
 * Time: 3:03 PM
 */

/**
 * Fibonacci: Calculates a fibonacci sequence
 *
 * @author <a href="mailto:jtroxel@yahoo.com">John Troxel</a>
 */
public class Fibonacci {
  public Fibonacci() {
  }

  public Integer calc(Integer idx) {
    if (idx <= 2) {
      return 1;
    } else {
      return calc(idx-2) + calc(idx-1);
    }
  }
}

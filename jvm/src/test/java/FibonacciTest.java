import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * User: jtroxel
 * Date: 8/21/15
 * Time: 3:10 PM
 */
public class FibonacciTest {

  @Test
  public void calc() throws Exception {
    Integer trailOne = 1, trailTwo = 1;
    Fibonacci fib = new Fibonacci();
    for (int i=1; i <= 10; i++) {
      Integer newFib = fib.calc(i);
      System.out.println(newFib);
      System.out.println(trailOne + " " + trailTwo);
      assertEquals((long) newFib, testFib(i, trailOne, trailTwo));
      trailTwo = trailOne;
      trailOne = newFib;
    }
  }

  private int testFib(Integer idx, Integer trailOne, Integer trailTwo) {
    if (idx < 3)
      return 1;
    else
      return trailOne + trailTwo;
  }
}

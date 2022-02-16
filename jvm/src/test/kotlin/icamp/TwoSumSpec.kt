package icamp

import icamp.ArrayFun.sum2for
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/*
2 Sum Problem: Given a *sorted* array of integers, find two numbers in the array that sum to a given integer target.
For example, if a = [1,2,3,5,6,7] and target = 11, the answer will be 5 and 6.

Questions to Clarify
Q. How do return the output?  A. Return it as a pair of numbers.
Q. What to return if there is no result?  A. Return null.
Q. Can the array have duplicates?  A. Yes
Q. If there are more than one pair that qualify, which pair should I return?
   For example, if a=[2,3,4,5] and target=7, the answer could be either {2,5} or {3,4}
   A. Return whichever you like. As long as you return a correct pair, it’s fine.

Solution Options / Notes
 - Need to compare idx..length-2 with 1..length-1, sounds nested, O(n**2).  Could keep founds.
 - Founds:  - Array of vals: basically have to keep both, space O(2n). still have to iterate.
            - Map of (sum-val) -> val.  Then can compare current val with map, return curVal, map[curVal] if exists
 - Traversal:  Single? time = O(n), space = O(2n)
               Double? time = O(n/2), space = O(n/2) == same
               Arr is SORTED, so:  Can do double, adjust based on Target.  Increase start if < T, Increase end if < T.


 */

class TwoSumSpec : DescribeSpec({
    context("Double Traversal") {
        context("Prime Case") {
            val exCase: IntArray = intArrayOf(1,2,3,5,6,7)
            val exTarget: Int = 11; // --> [5, 6]
            sum2for(exCase, exTarget) shouldBe intArrayOf(5,6)
        }
        context("Base Case: Array of 1, null") {
            val baseCase: IntArray = intArrayOf(13)
            sum2for(baseCase, 11) shouldBe null
        }
        context("Base Case: Array of 2 hit") {
            val baseCase: IntArray = intArrayOf(13, 2)
            sum2for(baseCase, 15) shouldBe baseCase
        }
        context("Edge Case empty array") {
            val edgeCase: IntArray = intArrayOf()
            sum2for(edgeCase, 15) shouldBe null
        }
        context("Example Case: Array of 3, Last 2") {
            val exCase: IntArray = intArrayOf(3, 4, 5)
            sum2for(exCase, 9) shouldBe intArrayOf(4,5)
        }
        context("Example Case: Array of 3, First 2") {
            val exCase: IntArray = intArrayOf(3, 4, 5)
            sum2for(exCase, 7) shouldBe intArrayOf(3,4)
        }
        context("Example Case: Array with dupes") {
            val exCase: IntArray = intArrayOf(1,1,3,4,4,4,6,6,11)
            sum2for(exCase, 8) shouldBe intArrayOf(4,4)
        }
        xcontext("reverseArray (TODO)") {
            describe("arr has 1 and given") {
                it("returns first 2") {
                    Summer().twoSum(2, intArrayOf(1, 2, 3)) shouldBe intArrayOf(1, 2)
                }
            }
        }

    }
})


package icamp

import icamp.PrefixSum.subarrayWithSum
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: Prefix Sum 1
Given an array of integers, both -ve and +ve, find a contiguous subarray that sums to 0.

Prime Example: [2,4,-2,1,-3,5,-3] --> [4,-2,1,-3]

Questions to Clarify:
Q: How should I return the output?  A: Return the starting and ending indices of the subarray.
Q: What to return if the array is empty or null?  A: Return null.
Q: What to return if no subarray is found?  A: Return null.
Q: What to do if there are multiple subarrays?  A: Return any one, first

Solution Options / Notes:
 - Looks like "Array Prefix Sum" use case
 - Single traversal, looking for a cumulative sum = a previous one, meaning that the values between must zero out
 - keeping sums in a map sum -> index
 - if (curSum == target) -> Pair(0,i) - base
 - if (curSum == arr[i] -> Pair(i,i) - base
 - if (curSum = found[curSum]) -> new Pair(found[curSum] + 1, i - 1) - 0 case
 - if (curSum = found[target - curSum]) -> new Pair(found[curSum] + 1, i - 1) -- [2, -1, 2] with 1
 */

class PrefixSumSpec : DescribeSpec({
    context("Prefix Sum 1") {
        context("Prime Case: ...") {
            val pCase: IntArray = intArrayOf(2,4,-2,1,-3,5,-3)
            subarrayWithSum(pCase, 0) shouldNotBe null
            subarrayWithSum(pCase, 0) shouldBeEqualToComparingFields Pair(1, 4);
        }
/*
        context("Base Case: ...") {
            val baseCase: IntArray = intArrayOf()
            subarrayWithSum(baseCase) shouldBe null
        }
        context("Edge Case: empty array") {
            val edgeCase: IntArray = intArrayOf()
            subarrayWithSum(edgeCase) shouldBe null
        }
        context("Example Case: ...") {
            val exCase: IntArray = intArrayOf()
            subarrayWithSum(exCase) shouldBe null
        }
*/
    }
})


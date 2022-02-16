package icamp

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


/*
Given an array of numbers, replace each even number with two of the same number.
   Example: [1,2,5,6,8, , , ,] -> [1,2,2,5,6,6,8,8].
   Assume that the array has the exact amount of space to accommodate the result.
   Questions to Clarify:
   Q. How do you want to return the result? A. Modify the input array.
   Q. What would an empty element contain?  A. -1

   Solution Concept(s):
   - Forward traverse, move i+1+ elements over 1.  A lot of rewriting.  Time=O(n**2)
   - Since the open slots are at the end, maybe reverse traversal
     - Find the last valid #, write to end (even write end again).  Iterate lastNum, keep the nextEmpty

 */
class ReverseTraversalReplaceSpec : DescribeSpec({

    context("doubleEvens") {

        context("Example Case") {
            val exCase: IntArray = intArrayOf(1, 2, 5, 6, 8, -1, -1, -1)
            ArrayFun.doubleEvens(exCase) shouldBe intArrayOf(1, 2, 2, 5, 6, 6, 8, 8)
        }
        context("Base Case 1 Even") {
            val baseCase: IntArray = intArrayOf(2, -1)
            ArrayFun.doubleEvens(baseCase) shouldBe intArrayOf(2, 2)
        }
        context("Base Case 1 Odd") {
            val baseCase: IntArray = intArrayOf(1)
            ArrayFun.doubleEvens(baseCase) shouldBe intArrayOf(1)
        }
        context("Edge Case empty array") {
            val edgeCase: IntArray = intArrayOf()
            ArrayFun.doubleEvens(edgeCase) shouldBe intArrayOf()
        }
        context("Edge Case: array with no slots") {
            val edgeCase: IntArray = intArrayOf(2)
            shouldThrow<IllegalArgumentException> {
                ArrayFun.doubleEvens(edgeCase) shouldBe intArrayOf(Any() as Int)
            }

        }
        context("Example Case: Multiple with no evens ") {
            val exCase: IntArray = intArrayOf(1, 3, 5, 5, 5)
            ArrayFun.doubleEvens(exCase) shouldBe intArrayOf(1, 3, 5, 5, 5)
        }
        context("Example Case: Multiple with all evens ") {
            val exCase: IntArray = intArrayOf(0, 2, 4, -1, -1, -1)
            ArrayFun.doubleEvens(exCase) shouldBe intArrayOf(0, 0, 2, 2, 4, 4)
        }
    }

});



package icamp

import icamp.ArrayFun.reverseArray
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/*
PROBLEM:
Given an array, reverse the order of its elements.

EXAMPLE(S)
For example, [3,5,2,5,2,3,9] → [9,3,2,5,2,5,3] (Prime Ex)

Questions to Clarify:
Q. Should the output be a new array or the existing array modified?
A. Modify the existing array.

Q. What about a null or empty value
A. Raise exception/require non-null, or return empty

ANALYSIS
- Need to traverse forward and backwards (bw only would require 2nd array)
- swap values at pointers, need var for change

SOLUTION CONCEPTS
 - Traversing from both ends. save arr[last], arr[last] = arr[first], arr[first] = saved
    O(n) time, O(n) space
 - Traverse one way?  From 0 to length-1, point idx at arr[length-idx]...  loose original idx value



 */

class ReverseArraySpec : DescribeSpec({
    context("reverseArray") {
        context("Prime Case (Odd length)") {
            val exCase: IntArray = intArrayOf(3, 5, 2, 5, 2, 3, 9)
            reverseArray(exCase) shouldBe intArrayOf(9, 3, 2, 5, 2, 5, 3)
        }
        context("Base Case: Array of 1") {
            val baseCase: IntArray = intArrayOf(13)
            reverseArray(baseCase) shouldBe intArrayOf(13)
        }
        context("Base Case: Array of 2 (even)") {
            val baseCase: IntArray = intArrayOf(13, 7)
            reverseArray(baseCase) shouldBe intArrayOf(7, 13)
        }
        context("Edge Case empty array") {
            val edgeCase: IntArray = intArrayOf()
            reverseArray(edgeCase) shouldBe intArrayOf()
        }
        context("Example Case:  Even length") {
            val exCase: IntArray = intArrayOf(3, 5, 2, 5, 2, 3, 9, 3)
            reverseArray(exCase) shouldBe intArrayOf(3, 9, 3, 2, 5, 2, 5, 3)
        }

    }
})


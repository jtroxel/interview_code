package icamp

import icamp.ArrayFun.partitionBy
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: Array Partition
 You are given an array of integers
 Rearrange the array so that all zeroes are at the beginning of the array.

Prime Example:
 [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]

Questions to Clarify:
Q. Optimize for space or time?  A. Time more important, but both.
Q. Has to be in the Ex order, same L->R ordering as input?  A. NO

Additional Cases:
  Base: array[2] of just 0, 0
  Base: array[2] of just non 0s ?
  Base: array[2] of [0,2] -> swapped

Solution Options / Notes:
 - Brute force, allocate 2d array, process input into it.  O(n) time, O(2n) space.
 */

class ArrPartitionSpec : DescribeSpec({
    fun partitionRule() = { arrVal: Int -> arrVal == 0 }
    context("Array Partition") {
        context("Prime Case: Mixed array, P2 order not important") {
            val pCase: IntArray = intArrayOf(4,2,0,1,0,3,0)
            partitionBy(pCase, partitionRule()) shouldBe intArrayOf(0,0,0,3,1,2,4)
        }
        context("Base Case: [0,0] -> [0,0]") {
            val baseCase: IntArray = intArrayOf(0,0)
            partitionBy(baseCase, partitionRule()) shouldBe baseCase
        }
        context("Base Case: [1,0] -> [0,1]") {
            val baseCase: IntArray = intArrayOf(1,0)
            partitionBy(baseCase, partitionRule()) shouldBe intArrayOf(0,1)
        }
        context("Edge Case: empty array") {
            val edgeCase: IntArray = intArrayOf()
            partitionBy(edgeCase, partitionRule()) shouldBe null
        }
        xcontext("Example Case: ...") {
            val exCase: IntArray = intArrayOf()
            partitionBy(exCase, partitionRule()) shouldBe null
        }
    }
})


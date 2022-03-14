package icamp

import icamp.ArrayFun;
import icamp.Subarray.maxSumSubarray


import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: Subarray Sum #1

https://docs.google.com/document/d/1l8jLRPiX8APpCL5UVOWeKyTxoG6KV6YWWtGIdDNsOQU/edit#


Prime Example:

Questions to Clarify:
Q. 

Solution Options / Notes:
 - 
 */

class SubarraySpec : DescribeSpec({
    context("Subarray Sum #1") {
        context("Prime Case: [-2, -3, 4, -1, -2, 1, 5, -1]") {
            val pCase: IntArray = intArrayOf(-2, -3, 4, -1, -2, 1, 5, -1)
            val subject = maxSumSubarray(pCase)
            subject shouldNotBe emptyArray<Int>()
            subject shouldBe intArrayOf(4, -1, -2, 1, 5)
        }
        context("Base Case: [2]") {
            val baseCase: IntArray = intArrayOf(2)
            maxSumSubarray(baseCase) shouldBe baseCase
        }
        xcontext("Edge Case: empty array") {
            val edgeCase: IntArray = intArrayOf()
            maxSumSubarray(edgeCase) shouldBe intArrayOf()
        }
        context("Example Case: 1,2,3,-1,-2,-3,-4,5") {
            val exCase: IntArray = intArrayOf(1, 2, 3, -1, -2, -3, -4, 5)
            val subject = maxSumSubarray(exCase)
            subject shouldNotBe emptyArray<Int>()
            subject shouldBe intArrayOf(1, 2, 3)
        }
        context("Example Case: 1,2,-1,2,-3,2,-5") {
            val exCase: IntArray = intArrayOf(1, 2, -1, 2, -3, 2, -5)
            val subject = maxSumSubarray(exCase)
            subject shouldNotBe emptyArray<Int>()
            subject shouldBe intArrayOf(1, 2, -1, 2)
        }
        context("Example Case (Wikipedia): −2, 1, −3, 4, −1, 2, 1, −5, 4") {
            val exCase: IntArray = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val subject = maxSumSubarray(exCase)
            subject shouldNotBe emptyArray<Int>()
            subject shouldBe intArrayOf(4, -1, 2, 1)
        }

    }
})


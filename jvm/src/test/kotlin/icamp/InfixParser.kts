package icamp

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: Evaluating Infix Arithmetic

Prime Example:
Trivial:
Other :

Clarifications:
Q. 

Solution Discussion:
 - 
 */

class TwoSumSpec : DescribeSpec({
    context("Evaluating Infix Arithmetic") {

        context("Prime Case: ...") {
            val pCase = ArithmeticEvaluator("1+2")
            pCase.eval() shouldNotBe null
        }
/*
        context("Trivial Case: ...") {
            val baseCase: IntArray = intArrayOf()
            evluateEx(baseCase) shouldBe null
        }
        context("Base Case: ...") {
            val baseCase: IntArray = intArrayOf()
            evluateEx(baseCase) shouldBe null
        }
        context("Edge Case: empty array") {
            val edgeCase: IntArray = intArrayOf()
            evluateEx(edgeCase) shouldBe null
        }
        context("XXX Case: ...") {
            val exCase: IntArray = intArrayOf()
            evluateEx(exCase) shouldBe null
        }
*/
    }
})


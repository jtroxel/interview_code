package leetcode

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: Regions Cut By Slashes

Prime Example:
Trivial:
Other :

Clarifications:
Q. 

Solution Discussion:
 - 
 */

class RegionsCutBySlashesSpec : DescribeSpec({
    context("Regions Cut By Slashes") {
        var subject = RegionsCutBySlashes()
        context("Prime Case: ...") {
            val pCase: Array<String> = arrayOf("")
            subject.regionsBySlashes(pCase) shouldBe 1
        }
        context("Trivial Case: ...") {
            val baseCase: Array<String> =
                arrayOf(" /", "/ ")
            subject.regionsBySlashes(baseCase) shouldBe 2
        }
/*
        context("Base Case: ...") {
            val baseCase: IntArray = intArrayOf()
            regionsBySlashes(baseCase) shouldBe null
        }
        context("Edge Case: empty array") {
            val edgeCase: IntArray = intArrayOf()
            regionsBySlashes(edgeCase) shouldBe null
        }
        context("XXX Case: ...") {
            val exCase: IntArray = intArrayOf()
            regionsBySlashes(exCase) shouldBe null
        }
*/
    }
})


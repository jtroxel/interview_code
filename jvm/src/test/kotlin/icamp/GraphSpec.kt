package icamp

import icamp.Graph.Node
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/*
PROBLEM: DFS - Value exists

Prime Example:
Trivial:
Other :

Clarifications:
Q. 

Solution Discussion:
 - 
 */

class GraphSpec : DescribeSpec({
    context("Graph - build from map of edges") {
        context("Prime Case: ...") {
            val pCase: Graph = Graph(
                mapOf(
                    1 to arrayListOf(2, 3),
                    2 to arrayListOf(4),
                    3 to arrayListOf(4, 5),
                    4 to arrayListOf(6),
                    5 to arrayListOf(6),
                    7 to arrayListOf(8) // Disconnected segment
                )
            )
            pCase shouldNotBe null
            pCase.roots shouldNotBe emptyArray<Node>()
            pCase.roots.size shouldBe 2
        }
    }
    context("DFS - Value exists") {
        context("Prime Case: ...") {
            val pCase: Graph = Graph(
                mapOf(
                    1 to arrayListOf(2, 3),
                    2 to arrayListOf(4),
                    3 to arrayListOf(4, 5),
                    4 to arrayListOf(6),
                    5 to arrayListOf(6),
                    7 to arrayListOf(8) // Disconnected segment
                )
            )
            pCase.dfsExists(4) shouldBe true;
            pCase.dfsExists(8) shouldBe true;
            pCase.dfsExists(9) shouldBe false;
        }
//        context("Trivial Case: ...") {
//            val baseCase: IntArray = intArrayOf()
//            dfsExists(baseCase) shouldBe null
//        }
//        context("Base Case: ...") {
//            val baseCase: IntArray = intArrayOf()
//            dfsExists(baseCase) shouldBe null
//        }
//        context("Edge Case: empty array") {
//            val edgeCase: IntArray = intArrayOf()
//            dfsExists(edgeCase) shouldBe null
//        }
//        context("XXX Case: ...") {
//            val exCase: IntArray = intArrayOf()
//            dfsExists(exCase) shouldBe null
//        }
    }
})


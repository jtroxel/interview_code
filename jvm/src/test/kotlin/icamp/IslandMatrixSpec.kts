package icamp

import io.kotest.core.spec.style.DescribeSpec

class IslandMatrixSpec : DescribeSpec({
    xcontext("Do Over") {

        describe("arr == [[1]]") {
            val array_1 = arrayOf(intArrayOf(1))
            var subject = IslandMatrix(array_1)

            it("returns 1") {
                assert(subject.calcIslands() == 1)
            }
        }
        describe("arr == [[0]]") {
            val array_0 = arrayOf(intArrayOf(0))
            var subject = IslandMatrix(array_0)

            it("returns 0") {
                assert(subject.calcIslands() == 0)
            }
        }
        describe("arr == [[0,1],[1,0]]") {
            val array_0 = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0))
            var subject = IslandMatrix(array_0)

            it("returns 0") {
                assert(subject.calcIslands() == 0)
            }
        }
        describe("arr == [[0,1],[0,1]]") {
            val array_0 = arrayOf(intArrayOf(0, 1), intArrayOf(0, 1))
            var subject = IslandMatrix(array_0)

            it("returns 1") {
                assert(subject.calcIslands() == 1)
            }
        }
    }
})

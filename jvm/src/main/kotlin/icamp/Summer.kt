package icamp

class Summer() {
    fun twoSum(target: Int, candidates: IntArray): Array<Int>? {
        var visited: Map<Int, Boolean> = HashMap()
        candidates.forEach {
            if (it >= target) return@forEach
            val targetDiff: Int = target - it
            if (visited.contains(targetDiff)) {
                return arrayOf(it, targetDiff)
            }
        }
        return null
    }
}

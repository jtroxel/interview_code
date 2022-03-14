package leetcode;

/*
PROBLEM:  528. Random Pick with Weight

Prime Example: [1,3] => ret 1 25%, 3 75%

Ex II:

[1,   3,   1,   2]
[1/7, 3/7, 1/7, 2/7]

Questions to Clarify:
Q. Same weights?  A: Yes
...

Solution Options / Notes:
 - Need to calculate w[i]/sum.  2nd Array?  Rolling sum?  rand mod ramd = len,
 - NO: Maybe can pick a random #, out of len*100, take (rand mod 100)/100 * len... iterate length from that #, i += w.
 - calc prefix sums--so each pf[i] is a range in a distribution out of the sum
 [1,   3,   1,   2]
 [1,   4,   5,   7] -> NOTE turns out sorted
 | _ | _ _ _ | _ | _ _ |
 -
 - gen random (0, ps[i.length-1]),
 - traverse ps arr
   - if ps[i] < rand: return i;
*/
class SampleDistribution {

    private int[] prefixSums;
    private int[] weights;
    private int distSize;
    public SampleDistribution(int[] w) {
        this.weights = w;
        this.distSize = w.length;
        // build prefixSum array, starting at 1
        this.prefixSums = new int[w.length];
        this.prefixSums[0] = w[0];
        for (int i = 1; i < distSize; i++) {
            this.prefixSums[i] = this.prefixSums[i-1] + w[i];
        }
        int[] p = this.prefixSums;
    }

    public int pickWeightedSlice() {
        int foo = this.distSize;
        int[] bar = this.prefixSums;
        double distance = this.prefixSums[this.distSize-1] * Math.random();
        // binary search on prefixSums, -> O(n log n)
        // TODO Fix:
        int low = 0, high = this.distSize;
        while (low < high) {
            int mid = low + (high-low) / 2;
            if (this.prefixSums[mid] < distance) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

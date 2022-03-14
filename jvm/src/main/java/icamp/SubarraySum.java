package icamp;

import java.util.Arrays;

import static java.lang.Math.max;

public class SubarraySum {
    public SubarraySum(int start, int end, int[] parArr) {
        this.start = start;
        this.sum = parArr[start];
        this.end = end;
        this.parArr = parArr;
    }

    /**
     * Using this as the left side SubSum, compare with curVal
     *
     * @return
     */

    // TODO:  Keep 2 subarrays:  leftMaxArr and workingArr, always update working from Lm.start to keep new possible max
    public SubarraySum findMaxRightFrom() {
        int sumLeft = this.sum;
        for (int i = this.end + 1; i <= this.parArr.length - 1; i++) {
            int valRight = this.parArr[i];
            // For each position, we compare rightVal, rightVal + leftSum, and just leftSum.
            // Calculate new localMax, valRight versus curSum
            int sumRight = sumLeft + valRight;
            if (valRight >= sumRight) {
                // New valRight is higher than other 2, start over here
                if (valRight >= sumLeft) {
                    this.start = this.end = i;
                    this.sum = sumLeft = valRight;
                }
            } else { // sumRight > valRight
                if (sumRight >= sumLeft) {
                    this.end = i;
                    this.sum = sumRight;
                }
            }
        }
        return this;
    }

    private int calculateSum(int start, int end, int[] parArr) {
        int retSum = parArr[start];
        for (int i = start + 1; i <= end; i++) {
            retSum += parArr[i];
        }
        return retSum;
    }

    public int[] parArr = null;
    public int start;
    public int end;
    public int sum;

    public int[] asArray() {
        return Arrays.copyOfRange(this.parArr, this.start, this.end);
    }
}

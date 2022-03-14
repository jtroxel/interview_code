package icamp;

class Subarray {

    public static int[] maxSumSubarray(int[] arr) {
        int curSum = 0;
        int maxSum = 0;
        int maxStart = 0;
        int maxEnd = maxStart;

        // Left to right, starting with [0,1].  maxSumLeft = arr[n-1] and curVal = arr[1]
        SubarraySum startLeftSum = new SubarraySum(0, 0, arr);
        //    Compare maxSumLeft (arr[0]..arr[n-1]), to arr[n]
        //    If curVal > curSum + curVal, curVal is max, reset start
        SubarraySum result = startLeftSum.findMaxRightFrom();

        return result.asArray();
    }


}


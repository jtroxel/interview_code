package icamp;

import java.util.HashMap;
import java.util.Map;

public class PrefixSum {
    public static Pair subarrayWithSum(int[] inputArr, int targetSum) {
        int prefixSum = 0;
        Map<Integer,Integer> foundSumsAt = new HashMap<>();
        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr == null || inputArr.length == 0) {
                return null;
            }
            int newVal = inputArr[i];
            // If we have seen this before, see if there is a matching value.
            int prefixSumCompliment = targetSum - newVal;
            if (foundSumsAt.containsKey(prefixSumCompliment)) {
                return new Pair(foundSumsAt.get(prefixSumCompliment) + 1, i);
            }
            prefixSum += newVal;
            if (prefixSum == targetSum) {
                return new Pair(0, i);
            }
            foundSumsAt.put(prefixSum, i);
        }
        return null;
    }

}

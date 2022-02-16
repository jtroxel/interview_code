package icamp;

import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ArrayFun {
    public static final int ARRAY_OPEN_SPACE_VALUE = -1;

    public static int[] reverseArray(int[] inArray) {
        assert inArray != null;
        // Just return input if array is size 0 or 1
        if (inArray.length < 2) {
            return inArray;
        }
        swapAll(inArray);
        return inArray;
    }

    public static int[] partitionBy(int[] input, Function<Integer, Boolean> fn) {
        int arrSize = input.length;
        int[] retArr = new int[arrSize];
        int part1idx = 0;
        int part2idx = 0;
        for (int inVal : input) {
            // If current partition2 val matched partitioning, move to end of p1
            if (fn.apply(inVal)) {
                input[part1idx++] = inVal; // put new val at end of part1

            } else { // add to returnArr in order
                retArr[part2idx++] = inVal;
            }
        }
        System.arraycopy(retArr, 0, input, part1idx, part2idx);
        retArr = null;
        return input;
    }

    // 1,2,3,5,6,7
    public static int[] sum2for(int[] inArray, int target) {
        if (inArray == null || inArray.length == 0) {
            return null;
        }
        return findSumDouble(inArray, target, 0, inArray.length - 1);
    }

    @Nullable
    private static int[] findSumDouble(int[] inArray, int target, int start, int end) {
        if (start == end) {
            return null;
        }
        int check = checkSum(inArray, start, end, target);
        if (check < 0) {
            return findSumDouble(inArray, target, start + 1, end);
        } else if (check > 0) {
            return findSumDouble(inArray, target, start, end - 1);
        }
        return new int[]{inArray[start], inArray[end]};
    }

    private static int checkSum(int[] inArray, int start, int end, int target) {
        return inArray[start] + inArray[end] - target;
    }

    private static void swapAll(int[] inArray) {

        // Switch the first/last element
        swapElement(inArray, 0, inArray.length - 1);
    }

    private static void swapElement(int[] inArray, int startPos, int endPos) {
        int lastVal = inArray[endPos]; // keep original value
        inArray[endPos] = inArray[startPos];
        inArray[startPos] = lastVal;
        // If we just swapped the middle 2, done
        if (endPos - startPos < 2) {
            return;
        }
        swapElement(inArray, startPos + 1, endPos - 1);
    }

    public static int[] doubleEvens(int[] arrayWithSpace) throws IllegalArgumentException {
        int endSlot = arrayWithSpace.length - 1;
        int lastNum = endSlot; // Start traversal backwards from last

        while (lastNum >= 0) {
            lastNum = getLastNonOpen(arrayWithSpace, lastNum);
            // No matter what, copy last
            endSlot = copyToLast(arrayWithSpace, lastNum, endSlot);
            // And if even, copy another
            if (isEven(arrayWithSpace[lastNum])) {
                endSlot = copyToLast(arrayWithSpace, lastNum, endSlot);
            }
            lastNum--;
        }
        return arrayWithSpace;
    }

    private static int getLastNonOpen(int[] arrayWithSpace, int lastNum) {
        while (arrayWithSpace[lastNum] == ARRAY_OPEN_SPACE_VALUE) {
            lastNum--;
        }
        return lastNum;
    }

    private static int copyToLast(int[] arrayWithSpace, int fromIdx, int toIdx) {
        if (toIdx < 0) {
            throw new IllegalArgumentException("Missing slot for even");
        }
        arrayWithSpace[toIdx] = arrayWithSpace[fromIdx];
        return --toIdx;
    }

    private static boolean isEven(int arrayWithSpace) {
        return arrayWithSpace % 2 == 0;
    }
}

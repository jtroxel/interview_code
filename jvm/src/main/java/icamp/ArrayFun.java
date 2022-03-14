package icamp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    //             withClue("[4,2,0,1,0,3,0]") {
    public static int[] partitionBy(int[] input, Function<Integer, Boolean> fn) {
        int arrSize = input.length;
//        int[] retArr = new int[arrSize];
        int part1end = 0;
        int part2tart = 0;
        for (int inVal : input) {
            // If current partition2 val matched partitioning
            if (fn.apply(inVal)) {
                moveLastFirst(input, part1end, part2tart);
                part1end += 1; // inc end of P1

            } else {
                part2tart++; // inc start of P2
            }
        }
        return input;
    }

    private static void moveLastFirst(int[] input, int start, int end) {
        int hold = input[end];
        //Shuffle left
        while (end > start) {
            input[end - 1] = input[end--];
        }
        input[start] = hold;
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
        swapAllElements(inArray, 0, inArray.length - 1);
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

    private static void swapAllElements(int[] inArray, int startPos, int endPos) {
        swapElement(inArray, startPos, endPos);
        // If we just swapped the middle 2, done
        if (endPos - startPos < 2) {
            return;
        }
        swapElement(inArray, startPos + 1, endPos - 1);
    }

    public static void swapElement(int[] inArray, int startPos, int endPos) {
        int lastVal = inArray[endPos]; // keep original value
        inArray[endPos] = inArray[startPos];
        inArray[startPos] = lastVal;
    }

    private static boolean isEven(int arrayWithSpace) {
        return arrayWithSpace % 2 == 0;
    }
}

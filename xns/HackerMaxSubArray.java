package com.example.xns;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HackerMaxSubArray {

    // Find the maximum possible sum in arr[]
    // such that arr[m] is part of it
    private static Result maxCrossingSum(int arr[], int l, int m, int h) {
        // Include elements on left of mid.
        int sum = 0;
        int maxLeft = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum = sum + arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        // Include elements on right of mid
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        int maxRight = arr.length - 1;
        for (int i = m + 1; i <= h; i++) {
            sum = sum + arr[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        // Return sum of elements on left
        // and right of mid
        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }

    // Returns sum of maxium sum subarray
    // in aa[l..h]
    private static Result maxSubArray(int[] A, int low, int high) {
        // Base Case: Only one element
        if (low == high) {
            return new Result(low, high, A[low]);
        }

        // Find middle point
        int mid = (low + high) / 2;

        /* Return maximum of following three
        possible cases:
        a) Maximum subarray sum in left half
        b) Maximum subarray sum in right half
        c) Maximum subarray sum such that the
        subarray crosses the midpoint */
        Result leftSubArray = maxSubArray(A, low, mid);
        Result rightSubArray = maxSubArray(A, mid + 1, high);
        Result maxCrossingSubArray = maxCrossingSum(A, low, mid, high);
        int leftSum = leftSubArray.sum;
        int rightSum = rightSubArray.sum;
        int crossSum = maxCrossingSubArray.sum;

        if (leftSum > rightSum && leftSum > crossSum) {
            return new Result(leftSubArray.low, leftSubArray.high, leftSubArray.sum);
        } else if (rightSum > leftSum && rightSum > crossSum) {
            return new Result(rightSubArray.low, rightSubArray.high, rightSubArray.sum);
        } else {
            return new Result(maxCrossingSubArray.low, maxCrossingSubArray.high,
                maxCrossingSubArray.sum);
        }
    }

    public static class Result {
        public int low;
        public int high;
        public int sum;

        public Result(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    /* Driver program to test maxSubArray */
    public static Result maxSubArray(int[] arr) {
        return maxSubArray(arr, 0, arr.length - 1);
    }

    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] arr) {
        int[] solution = new int[2];
        Result result = maxSubArray(arr);
        solution[0] = result.sum;
        solution[1] = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) {
                solution[1] += arr[i];
            }
        }
        if(solution[1] == 0) {
            solution[1] = solution[0];
        }
        return solution;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter =
            new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = maxSubarray(arr);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

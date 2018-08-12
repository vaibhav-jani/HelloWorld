package com.example.xns;

// A Divide and Conquer based Java
// program for maximum subarray sum
// problem

import java.util.Arrays;

class MaxSubArray {

    private static Result maxCrossingSum(int arr[], int l, int m, int h) {

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

        Result result = new Result(maxLeft, maxRight, leftSum + rightSum);
        System.out.print(
            "max cross : " + Arrays.toString(getSubArray(arr, l, h)) + " : " + printResult(arr,
                result));
        System.out.println("\n--------------------");
        return result;
    }

    private static Result maxSubArray(int[] A, int low, int high) {

        int[] subArray = getSubArray(A, low, high);
        System.out.println("called : " + Arrays.toString(subArray));

        if (low == high) {
            Result result = new Result(low, high, A[low]);
            System.out.println("result : " + printResult(A, result));
            return result;
        }

        int mid = (low + high) / 2;
        System.out.println("\n--------------------");
        Result leftSubArray = maxSubArray(A, low, mid);
        System.out.println("\n--------------------");
        Result rightSubArray = maxSubArray(A, mid + 1, high);
        System.out.println("\n--------------------");
        Result maxCrossingSubArray = maxCrossingSum(A, low, mid, high);
        int leftSum = leftSubArray.sum;
        int rightSum = rightSubArray.sum;
        int crossSum = maxCrossingSubArray.sum;

        Result result;
        if (leftSum > rightSum && leftSum > crossSum) {
            //return new Result(leftSubArray.low, leftSubArray.high, leftSubArray.sum);
            result = leftSubArray;
        } else if (rightSum > leftSum && rightSum > crossSum) {
            //return new Result(rightSubArray.low, rightSubArray.high, rightSubArray.sum);
            result = rightSubArray;
        } else {
            /*return new Result(maxCrossingSubArray.low, maxCrossingSubArray.high,
                maxCrossingSubArray.sum);*/
            result = maxCrossingSubArray;
        }

        System.out.println(
            "leftSum : " + leftSum + ", rightSum : " + rightSum + ", crossSum : " + crossSum);
        System.out.println("result : " + printResult(A, result));
        return result;
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

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrString[i]);
        }*/

        int[] arr = { -1, 2, 3, 4, -5 };
        Result result = maxSubArray(arr);
        System.out.println("=====================");
        System.out.println(printResult(arr, result));
    }

    private static String printResult(int[] arr, Result result) {

        int[] subArray = getSubArray(arr, result.low, result.high);
        int j = 0;
        for (int i = result.low; i <= result.high; i++) {
            subArray[j++] = arr[i];
        }

        return Arrays.toString(subArray) + ", sum : " + result.sum;
    }

    private static int[] getSubArray(int[] arr, int low, int high) {

        if (low == high) {
            int[] subArray = new int[1];
            subArray[0] = arr[low];
            return subArray;
        }

        int[] subArray = new int[high - low + 1];
        int j = 0;
        for (int i = low; i <= high; i++) {
            subArray[j++] = arr[i];
        }
        return subArray;
    }
}
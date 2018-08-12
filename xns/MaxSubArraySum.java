package com.example.xns;// A Divide and Conquer based Java
// program for maximum subarray sum
// problem

import java.util.Arrays;
import java.util.Scanner;

class MaxSubArraySum {

    // Find the maximum possible sum in arr[] 
    // such that arr[m] is part of it
    private static int maxCrossingSum(int arr[], int l, int m, int h) {
        // Include elements on left of mid.
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum = sum + arr[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        // Include elements on right of mid
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = m + 1; i <= h; i++) {
            sum = sum + arr[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        // Return sum of elements on left
        // and right of mid
        return leftSum + rightSum;
    }

    // Returns sum of maxium sum subarray 
    // in aa[l..h]
    private static int maxSubArraySum(int arr[], int l, int h) {
        // Base Case: Only one element
        if (l == h) {
            return arr[l];
        }

        // Find middle point
        int m = (l + h) / 2;
 
    /* Return maximum of following three 
    possible cases:
    a) Maximum subarray sum in left half
    b) Maximum subarray sum in right half
    c) Maximum subarray sum such that the 
    subarray crosses the midpoint */
        return Math.max(Math.max(maxSubArraySum(arr, l, m), maxSubArraySum(arr, m + 1, h)),
            maxCrossingSum(arr, l, m, h));
    }

    /* Driver program to test maxSubArray */
    public static int maxSubArraySum(int[] arr) {
        return maxSubArraySum(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrString[i]);
        }

        //arr = insertionSort(arr);
        //arr = selectionSort(arr);
        //arr = bubbleSort(arr);
        int sum = maxSubArraySum(arr);
        System.out.println("maxSubArray : " + sum);
    }
}
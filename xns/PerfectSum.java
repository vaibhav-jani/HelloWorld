package com.example.xns;

import java.util.ArrayList;

// A Java program to count all subsets with given sum.

public class PerfectSum {
    // dp[i][j] is going to store true if sum j is
    // possible with array elements from 0 to i.
    static boolean[][] dp;

    static void display(ArrayList<Integer> v) {
        System.out.println(v);
    }

    // A recursive function to print all subsets with the
    // help of dp[][]. Vector p[] stores current subset.
    static void printSubsetsRec(int arr[], int i, int sum, ArrayList<Integer> p) {
        // If we reached end and sum is non-zero. We print
        // p[] only if arr[0] is equal to sum OR dp[0][sum]
        // is true.
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            display(p);
            p.clear();
            return;
        }

        // If sum becomes 0
        if (i == 0 && sum == 0) {
            display(p);
            p.clear();
            return;
        }

        // If given sum can be achieved after ignoring
        // current element.
        if (dp[i - 1][sum]) {
            // Create a new vector to store path
            ArrayList<Integer> b = new ArrayList<>();
            b.addAll(p);
            printSubsetsRec(arr, i - 1, sum, b);
        }

        // If given sum can be achieved after considering
        // current element.
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            printSubsetsRec(arr, i - 1, sum - arr[i], p);
        }
    }

    // Prints all subsets of arr[0..n-1] with sum 0.
    static void printAllSubsets(int arr[], int n, int sum) {
        if (n == 0 || sum < 0) {
            return;
        }

        dp = new boolean[n][sum + 1];

        printDp("INIT", arr);

        // Sum 0 can always be achieved with 0 elements
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        printDp("Sum 0 can always...", arr);

        // Sum arr[0] can be achieved with single element
        if (arr[0] <= sum) {
            dp[0][arr[0]] = true;
        }

        printDp("arr[0] can always...", arr);

        // Fill rest of the entries in dp[][]
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < sum + 1; ++j) {
                dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] || dp[i - 1][j - arr[i]]) : dp[i - 1][j];
                printDp("Rest of i = " + i + ", j = " + j + ", arr[i] = " + arr[i], arr);
            }
        }

        if (dp[n - 1][sum] == false) {
            System.out.println("There are no subsets with" + " sum " + sum);
            return;
        }

        // Now recursively traverse dp[][] to find all
        // paths from dp[n-1][sum]
        ArrayList<Integer> p = new ArrayList<>();
        printSubsetsRec(arr, n - 1, sum, p);
    }

    private static void printDp(String tag, int[] arr) {
        System.out.println("\n");
        System.out.println("----- " + tag + " -----");
        System.out.println("----------------------------------------------");
        StringBuilder row1 = new StringBuilder();
        row1.append(" " + "        " + " ");
        for (int j = 0; j < dp[0].length; j++) {
            row1.append(" " + j + " ");
        }
        System.out.println(row1);
        System.out.println("______________________________________________");
        for (int i = 0; i < dp.length; i++) {
            StringBuilder row = new StringBuilder();
            row.append(" (" + arr[i] + ") " + i + "  | ");
            for (int j = 0; j < dp[i].length; j++) {
                row.append(dp[i][j] ? " T " : " F ");
            }
            System.out.println(row);
        }
        System.out.println("==============================================");
        System.out.println("\n");
    }

    //Driver Program to test above functions
    public static void main(String args[]) {
        int arr[] = { 3, 2, 7, 6, 1 };
        int n = arr.length;
        int sum = 9;
        printAllSubsets(arr, n, sum);
    }
}
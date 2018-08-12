package com.example.xns;

import java.util.Scanner;

public class SubsetEqualSplit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {

            int n = sc.nextInt();
            sc.nextLine();

            int[] arr = new int[n];
            String[] arrString = sc.nextLine().split(" ");

            int sum = 0;
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(arrString[j]);
                sum += arr[j];
            }

            if (sum % 2 != 0) {
                System.out.println("NO");
                continue;
            }
            sum = sum / 2;

            boolean[][] mat = new boolean[n][sum + 1];

            for (int j = 0; j < n; j++) {
                mat[j][0] = true;
            }

            for (int j = 0; j <= sum; j++) {
                if (arr[0] == j) {
                    mat[0][j] = true;
                } else {
                    mat[0][j] = false;
                }
            }

            for (int j = 1; j < n; j++) {
                for (int k = 1; k <= sum; k++) {
                    boolean includedCurrent = (k - arr[j]) >= 0 && mat[j - 1][k - arr[j]];
                    mat[j][k] = mat[j - 1][k] || includedCurrent;
                }
            }

            if (mat[n - 1][sum]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

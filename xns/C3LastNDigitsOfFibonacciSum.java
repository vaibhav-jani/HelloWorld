package com.example.xns;

// A Divide and Conquer based Java
// program for maximum subarray sum
// problem

import java.math.BigInteger;

class C3LastNDigitsOfFibonacciSum {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrString[i]);
        }*/

        //int[] arr = { 2, 3, -1, 1, 3 };
        int[] arr = { Integer.MAX_VALUE, 8, -3, 0, 1, 3, -2, 4, 5 };
        System.out.print(solution(50));
    }

    /*public static int solution(int K, int[] A) {
        Arrays.sort(A);
        int result = 0;
        int j = A.length - 1;
        int i = 0;
        while (i < j) {
            if (A[i] + A[j] == K) {
                result++;
                i++;
                j--;
            } else if ((A[i] + A[j]) < K) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }*/

    public static int solution(int N) {
        BigInteger[] A = new BigInteger[N + 2]; // 1 extra to handle case, n = 0
        int i;
        A[0] = new BigInteger("0");
        A[1] = new BigInteger("1");
        for (i = 2; i <= N; i++) {
            A[i] = A[i - 1].add(A[i - 2]);
        }
        BigInteger sum = A[N];
        String value = sum.toString();
        int length = value.length();
        int answer = Integer.parseInt(value);
        if(value.length() > 6) {
            answer = Integer.parseInt(value.substring(length- 6));
        }
        return answer;
    }


    //71%
    public static int solution1(int N) {
        BigInteger sum;
        BigInteger A = new BigInteger("0");
        BigInteger B = new BigInteger("1");
        BigInteger C;
        if (N == 0) {
            sum = A;
        } else {
            for (int i = 2; i <= N; i++) {
                C = A.add(B);
                A = B;
                B = C;
            }
            sum = B;
        }
        String value = sum.toString();
        int length = value.length();
        if (value.length() > 6) {
            return Integer.parseInt(value.substring(length - 6));
        } else {
            return Integer.parseInt(value);
        }
    }
}
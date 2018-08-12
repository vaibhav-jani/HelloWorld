package com.example.xns;

import java.math.BigInteger;

class fibonacci2 {

    static int solution(int N) {
        BigInteger F[][] = new BigInteger[][] {
            { new BigInteger("1"), new BigInteger("1") },
            { new BigInteger("1"), new BigInteger("0") }
        };
        if (N == 0) {
            return 0;
        }
        power(F, N - 1);
        BigInteger sum = F[0][0];
        String value = sum.toString();
        int length = value.length();
        if (value.length() > 6) {
            return Integer.parseInt(value.substring(length - 6));
        } else {
            return Integer.parseInt(value);
        }
    }

    static void multiply(BigInteger F[][], BigInteger M[][]) {
        BigInteger A = (F[0][0].multiply(M[0][0])).add(F[0][1].multiply(M[1][0]));
        BigInteger B = (F[0][0].multiply(M[0][1])).add(F[0][1].multiply(M[1][1]));
        BigInteger C = (F[1][0].multiply(M[0][0])).add(F[1][1].multiply(M[1][0]));
        BigInteger D = (F[1][0].multiply(M[0][1])).add(F[1][1].multiply(M[1][1]));
        F[0][0] = A;
        F[0][1] = B;
        F[1][0] = C;
        F[1][1] = D;
    }

    static void power(BigInteger F[][], int N) {
        BigInteger M[][] = new BigInteger[][] {
            { new BigInteger("1"), new BigInteger("1") },
            { new BigInteger("1"), new BigInteger("0") }
        };
        for (int i = 2; i <= N; i++) {
            multiply(F, M);
        }
    }

    /* Driver program to test above function */
    public static void main(String args[]) {
        int n = 1000000;
        System.out.println(solution(n));
    }
}
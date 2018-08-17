package world.hello.helloworld.xns;// A Divide and Conquer based Java
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

        /*System.out.println(solution(50));
        System.out.println(solution(100));
        System.out.println(solution(1000));
        System.out.println(solution(10000));
        System.out.println(solution(100000));
        System.out.println(solution(1000000));
        System.out.println(solution(100000001));
        long t = System.currentTimeMillis();
        System.out.println(solution(1000000002));
        System.out.println("t: " + (System.currentTimeMillis() - t));*/
        System.out.println(solution(6));
    }

    public static int solution1(int N) {
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
        if (value.length() > 6) {
            answer = Integer.parseInt(value.substring(length - 6));
        }
        return answer;
    }

    // 95%
    public static int solution1300(int N) {
        long sum;
        long A = 0;
        long B = 1;
        long C;
        long upperCheck = Integer.MAX_VALUE;
        if (N == 0) {
            sum = A;
        } else {
            for (int i = 2; i <= N; i++) {
                C = (A + B);
                A = B;
                if (A > upperCheck) {
                    A = (A % 1000000);
                }
                B = C;
                if (B > upperCheck) {
                    B = (B % 1000000);
                }
            }
            sum = B % 1000000;
        }
        return (int) sum;
    }

    // 95%
    public static int solution1500(int N) {
        long sum;
        long A = 0;
        long B = 1;
        long C;
        if (N == 0) {
            sum = A;
        } else {
            for (int i = 2; i <= N; i++) {
                C = (A + B);
                if (C < 0) {
                    C = (A % 1000000) + (B % 1000000);
                }
                A = B;
                B = C;
            }
            sum = B % 1000000;
        }
        return (int) sum;
    }

    // 71%
    public static int solution2(int N) {
        BigInteger sum;
        BigInteger A = new BigInteger("0");
        BigInteger B = new BigInteger("1");
        BigInteger C;
        if (N == 0) {
            sum = A;
        } else {
            for (int i = 2; i <= N; i++) {
                C = trimLsd(A.add(B), 9);
                A = B;
                B = C;
            }
            sum = B;
        }
        return trimLsd(sum, 6).intValue();
    }

    public static BigInteger trimLsd(BigInteger number, int count) {

        String value = number.toString();
        int length = value.length();
        if (value.length() > count) {
            return new BigInteger(value.substring(length - count));
        } else {
            return number;
        }
    }

    // 100%
    public static int solution(int N) {
        long F[][] = new long[][] { { 1, 1 }, { 1, 0 } };
        if (N == 0) {
            return 0;
        }
        power(F, N - 1);
        return (int) F[0][0];
    }

    public static void multiply(long F[][], long M[][]) {
        long x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        long y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        long z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        long w = F[1][0] * M[0][1] + F[1][1] * M[1][1];
        F[0][0] = x % 1000000;
        F[0][1] = y % 1000000;
        F[1][0] = z % 1000000;
        F[1][1] = w % 1000000;
    }

    public static void power(long F[][], long n) {
        if (n == 0 || n == 1) {
            return;
        }
        long M[][] = new long[][] { { 1, 1 }, { 1, 0 } };
        power(F, n / 2);
        multiply(F, F);
        if (n % 2 != 0) {
            multiply(F, M);
        }
    }

}

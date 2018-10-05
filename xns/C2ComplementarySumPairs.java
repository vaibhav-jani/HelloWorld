package world.hello.helloworld.xns;// A Divide and Conquer based Java
// program for maximum subarray sum
// problem

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Set;

class C2ComplementarySumPairs {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrString[i]);
        }*/

        //int[] arr = { 2, 3, -1, 1, 3 };
        int[] arr = { 1, 8, -3, 0, 1, 3, -2, 4, 5 };
        System.out.print(solution(6, arr));
    }

    /*public static int solution(int K, int[] A) {
        Arrays.sort(A);
        int result = 0;
        int j = A.length - 1;
        int i = 0;
        while (i <= j) {
            if (A[i] + A[j] == K) {
                result++;
                i++; // increment
                j--; // decrement
            } else if ((A[i] + A[j]) < K) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }*/

    public static int solution(int K, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] + A[j] == K) {
                    count++;
                }
            }
        }
        return count;
    }

    //100%
    public static int solution1(int K, int[] A) {
        int count = 0;

        HashMap<Long, Integer> countMap = new HashMap<>();
        for (long i : A) {
            if (countMap.get(i) == null) {
                countMap.put(i, 1);
            } else {
                countMap.put(i, countMap.get(i) + 1);
            }
        }

        Set<Long> keys = countMap.keySet();
        for (Long key : keys) {
            long complementaryKey = K - key;
            if (countMap.containsKey(complementaryKey)) {
                count += countMap.get(key) * countMap.get(complementaryKey);
            }
        }

        return count;
    }

    public static int solution2(int K, int[] A) {
        int count = 0;

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i : A) {
            if (countMap.get(i) == null) {
                countMap.put(i, 1);
            } else {
                countMap.put(i, countMap.get(i) + 1);
            }
        }

        Set<Integer> keys = countMap.keySet();
        for (Integer key : keys) {
            int complementaryKey = K - key;
            if (countMap.containsKey(complementaryKey)) {
                count += countMap.get(key) * countMap.get(complementaryKey);
            }
        }

        return count;
    }

    public static int solution3(int K, int[] A) {
        BigInteger count = new BigInteger("0");

        HashMap<Integer, BigInteger> countMap = new HashMap<>();
        for (int i : A) {
            if (countMap.get(i) == null) {
                countMap.put(i, new BigInteger("1"));
            } else {
                countMap.put(i, countMap.get(i).add(new BigInteger("1")));
            }
        }

        Set<Integer> keys = countMap.keySet();
        for (Integer key : keys) {
            int complementaryKey = K - key;
            if (countMap.containsKey(complementaryKey)) {
                count = count.add(countMap.get(key).multiply(countMap.get(complementaryKey)));
            }
        }

        return count.intValue();
    }
}
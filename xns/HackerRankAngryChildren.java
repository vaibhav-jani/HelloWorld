package com.example.xns;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HackerRankAngryChildren {

    // Complete the angryChildren function below.
    private static long angryChildren(int k, int[] packets) {

        packets = insertionSort(packets);

        int[] selected = new int[k];

        for (int i = 0; i < k; i++) {
            selected[i] = packets[i];
        }

        List<int[]> list = new ArrayList<>();
        getCombination(selected, 2, list);

        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] pair = list.get(i);
            if (pair[0] != pair[1]) {
                sum += Math.abs(pair[0] - pair[1]);
            }
        }
        return sum;
    }

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combination(int[] arr, int[] data, int start, int end, int index, int r,
        List<int[]> list) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            for (int j = 0; j < r; j++) {
                System.out.print(data[j] + " ");
            }
            System.out.println("");
            int[] combination = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                combination[i] = data[i];
            }
            list.add(combination);
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combination(arr, data, i + 1, end, index + 1, r, list);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combination()
    static void getCombination(int[] arr, int r, List<int[]> list) {
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temprary array 'data[]'
        combination(arr, data, 0, arr.length - 1, 0, r, list);
    }

    private static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    // Complete the angryChildren function below.
    private static long solution(int k, int[] a) {
        Arrays.sort(a);

        // Compute prefix sum
        int n = a.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        long d = 0;
        long ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (i < k) {
                d = d + i * a[i] - prefixSum[i - 1];
                ans = d;
            } else {
                long x = (prefixSum[i - 1] - prefixSum[i - k]) - (k - 1) * a[i - k];
                long y = (k - 1) * a[i] - (prefixSum[i - 1] - prefixSum[i - k]);
                if (d - x + y > 0) {
                    ans = Math.min(ans, d);
                    d = d - x + y;
                }
            }
        }

        return ans;
    }

    // Complete the angryChildren function below.
    private static long solution2(int k, int[] a) {
        Arrays.sort(a);

        // Compute prefix sum
        int n = a.length;
        BigInteger[] prefixSum = new BigInteger[n];
        prefixSum[0] = new BigInteger("" + a[0]);
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1].add(new BigInteger("" + a[i]));
        }

        BigInteger d = BigInteger.ZERO;
        BigInteger ans = new BigInteger("" + Long.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            if (i < k) {
                d = d.add(new BigInteger("" + i).multiply(new BigInteger("" + a[i])))
                    .subtract(prefixSum[i - 1]);
                ans = d;
            } else {
                BigInteger x = prefixSum[i - 1].subtract(prefixSum[i - k])
                    .subtract(
                        (new BigInteger("" + (k - 1))).multiply(new BigInteger("" + a[i - k])));
                BigInteger y = (new BigInteger("" + (k - 1))).multiply(new BigInteger("" + a[i]))
                    .subtract(prefixSum[i - 1].subtract(prefixSum[i - k]));
                BigInteger res = d.subtract(x).add(y);
                if (res.compareTo(BigInteger.ZERO) > 0) {
                    if (d.compareTo(ans) < 0) {
                        ans = d;
                    }
                    d = res;
                }
            }
        }

        return ans.longValue();
    }

    public static void main(String[] args) {

        int[] packets = {
            4504, 1520, 5857, 4094, 4157, 3902, 822, 6643, 2422, 7288, 8245, 9948, 2822, 1784, 7802,
            3142, 9739, 5629, 5413, 7232
        };

        System.out.println(solution(5, packets));
    }
}

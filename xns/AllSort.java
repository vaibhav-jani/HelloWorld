package com.example.xns;

import java.util.Arrays;
import java.util.Scanner;

public class AllSort {

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
        arr = MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
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

    private static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minPos = j;
                }
            }
            arr[minPos] = arr[i];
            arr[i] = min;
        }
        return arr;
    }

    private static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
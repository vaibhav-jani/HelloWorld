package com.example.xns;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PowerSet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        Set<Integer> arr = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(arrString[i]));
        }

        //Set<Set<Integer>> powerSet = powerSet(arr);
        Set<Set<Integer>> powerSet = powerSetNonRecursive(arr);
        for (Set<Integer> s : powerSet) {
            System.out.println(s);
        }
    }

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    static <T> Set<Set<T>> powerSetNonRecursive(Set<T> set) {

        /*setSize of power set of a set
        with setSize n is (2**n -1)*/
        long powSetSize = (long) Math.pow(2, set.size());
        Set<Set<T>> sets = new HashSet<Set<T>>();

        /*Run from counter 000..0 to
        111..1*/

        for (int i = 0; i < powSetSize; i++) {

            System.out.print(" i : " + i +   " | ");

            Set<T> subSet = new HashSet<>();
            List<T> setAsList = new ArrayList<>(set);
            for (int j = 0; j < setAsList.size(); j++) {

                /* Check if jth bit in the
                i is set If set then
                pront jth element from set */
                int shiftedByJ = 1 << j;
                if ((i & shiftedByJ) > 0) {
                    T element = setAsList.get(j);
                    subSet.add(element);
                    System.out.print(" " + element);
                } else {
                    System.out.print(" " + "X");
                }
            }
            sets.add(subSet);
            System.out.println();
        }

        return sets;
    }
}
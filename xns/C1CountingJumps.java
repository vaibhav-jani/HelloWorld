package world.hello.helloworld.xns;// A Divide and Conquer based Java
// program for maximum subarray sum
// problem

import java.util.HashSet;
import java.util.Set;

class C1CountingJumps {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        String[] arrString = sc.nextLine().split(" ");

        int n = arrString.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrString[i]);
        }*/

        //int[] arr = { 2, 3, -1, 1, 3 };
        int[] arr = { 1, 1, -1, 1 };
        System.out.print(solution(arr));
    }

    //100%
    public static int solution(int[] A) {

        int jumpCount = -1;
        int current = 0;

        Set<Integer> prePosition = new HashSet<>();
        while (current >= 0 && current < A.length) {
            current = current + A[current];
            jumpCount++;
            if (prePosition.contains(current)) {
                return -1;
            }
            prePosition.add(current);
        }
        jumpCount++;

        return jumpCount;
    }
}
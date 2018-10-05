package world.hello.helloworld.xns;

import java.util.HashSet;
import java.util.Set;

public class JumpOutOfArray {

    private static int countJumps(int[] a) {

        int currentPosition = 0;
        int jumpCount = 0;

        Set<Integer> prePosition = new HashSet<>();

        while (currentPosition >= 0 && currentPosition < a.length) {

            currentPosition += a[currentPosition];
            ++jumpCount;

            if (prePosition.contains(currentPosition)) {
                return -1;
            } else {
                prePosition.add(currentPosition);
            }
        }

        return jumpCount;
    }

    public static void main(String[] args) {

        int[] a = {1, 1, 1, 1, 0};
        //int[] a = {1, 1, -1, 2};
        //int[] a = {2, 3, -1, 1, 3};
        //int[] a = {1, 1, -1, 2};
        System.out.println("Jump count : " + countJumps(a));
    }
}

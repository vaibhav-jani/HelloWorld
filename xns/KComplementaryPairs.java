package world.hello.helloworld.xns;

import java.util.HashMap;
import java.util.Set;

public class KComplementaryPairs {

    private static int pairCount(int[] a, int k) {

        int totalCount = 0;

        HashMap<Long, Integer> countMap = new HashMap<>();
        for (long i : a) {
            if (countMap.get(i) == null) {
                countMap.put(i, 1);
            } else {
                countMap.put(i, countMap.get(i) + 1);
            }
        }

        Set<Long> keys = countMap.keySet();
        for (Long key : keys) {
            long complementaryKey = k - key;
            if (countMap.containsKey(complementaryKey)) {
                int pairCount = countMap.get(key) * countMap.get(complementaryKey);
                totalCount += pairCount;
                System.out.println("(" + key + ", " + complementaryKey + ") x " + pairCount);
            }
        }

        return totalCount;
    }

    public static void main(String[] args) {
        int[] a = { 1, 8, -3, 0, 1, 3, -2, 4, 5 };
        int k = 6;
        System.out.println("Pair count : " + pairCount(a, k));
    }
}

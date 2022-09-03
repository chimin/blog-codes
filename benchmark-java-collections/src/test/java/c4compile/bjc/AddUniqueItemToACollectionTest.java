package c4compile.bjc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddUniqueItemToACollectionTest {
    @Test
    public void test() {
        List<Integer> totalItemSizes = Arrays.asList(100000, 1000000);
        List<Integer> uniqueItemSizes = Arrays.asList(100, 1000, 10000);

        Map<String, Subtest> subtests = new LinkedHashMap<>();
        subtests.put("ArrayList", AddUniqueItemToACollectionTest::useArrayList);
        subtests.put("HashSet", AddUniqueItemToACollectionTest::useSet);
        subtests.put("Stream distinct to List", AddUniqueItemToACollectionTest::useStreamAndList);

        for (int totalItemSize : totalItemSizes) {
            for (int uniqueItemSize : uniqueItemSizes) {
                for (Map.Entry<String, Subtest> subtestEntry : subtests.entrySet()) {
                    System.gc();

                    Random random = new Random(0);
                    long startTime = System.currentTimeMillis();
                    subtestEntry.getValue().run(totalItemSize, uniqueItemSize, random);
                    long endTime = System.currentTimeMillis();

                    System.out.println(subtestEntry.getKey() + " | " +
                            totalItemSize + " total items | " +
                            uniqueItemSize + " unique items | " +
                            (endTime - startTime) + " ms");
                }
                System.out.println("---");
            }
        }
    }

    private static Collection<Integer> useSet(int totalItemSize, int uniqueItemSize, Random random) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalItemSize; i++) {
            set.add(random.nextInt(uniqueItemSize));
        }
        return set;
    }

    private static Collection<Integer> useArrayList(int totalItemSize, int uniqueItemSize, Random random) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < totalItemSize; i++) {
            int value = random.nextInt(uniqueItemSize);
            if (!list.contains(value)) {
                list.add(value);
            }
        }
        return list;
    }

    private static Collection<Integer> useStreamAndList(int totalItemSize, int uniqueItemSize, Random random) {
        return IntStream.range(0, totalItemSize)
                .mapToObj(i -> random.nextInt(uniqueItemSize))
                .distinct()
                .collect(Collectors.toList());
    }

    private interface Subtest {
        Collection<Integer> run(int totalItemSize, int uniqueItemSize, Random random);
    }
}

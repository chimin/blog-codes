package c4compile.bjc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AddUniqueItemToACollectionTest {
    @Test
    public void test() {
        List<Integer> totalItemSizes = Arrays.asList(100000, 1000000);
        List<Integer> uniqueItemSizes = Arrays.asList(100, 1000, 10000);
        List<Subtest> subtests = Arrays.asList(
                AddUniqueItemToACollectionTest::addItemsToASet,
                AddUniqueItemToACollectionTest::addItemsToAnArrayList);

        for (int totalItemSize : totalItemSizes) {
            for (int uniqueItemSize : uniqueItemSizes) {
                for (Subtest subtest : subtests) {
                    Random random = new Random(0);
                    long startTime = System.currentTimeMillis();
                    Collection<Integer> collection = subtest.run(totalItemSize, uniqueItemSize, random);
                    long endTime = System.currentTimeMillis();

                    System.out.println(collection.getClass() + " | " +
                            totalItemSize + " total items | " +
                            uniqueItemSize + " unique items | " +
                            (endTime - startTime) + " ms");
                }
            }
        }
    }

    private static Collection<Integer> addItemsToASet(int totalItemSize, int uniqueItemSize, Random random) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalItemSize; i++) {
            set.add(random.nextInt(uniqueItemSize));
        }
        return set;
    }

    private static Collection<Integer> addItemsToAnArrayList(int totalItemSize, int uniqueItemSize, Random random) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < totalItemSize; i++) {
            int value = random.nextInt(uniqueItemSize);
            if (!list.contains(value)) {
                list.add(value);
            }
        }
        return list;
    }

    private interface Subtest {
        Collection<Integer> run(int totalItemSize, int uniqueItemSize, Random random);
    }
}

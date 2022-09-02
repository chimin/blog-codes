package c4compile.bjc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class AddLastItemToAListTest {
    @Test
    public void test() {
        List<Integer> sizes = Arrays.asList(1000000, 2000000, 5000000, 10000000);
        for (int size : sizes) {
            List<Supplier<List<Integer>>> listSuppliers = Arrays.asList(
                    ArrayList::new,
                    () -> new ArrayList<>(size),
                    () -> Collections.synchronizedList(new ArrayList<>()),
                    LinkedList::new,
                    Stack::new
            );
            for (Supplier<List<Integer>> listSupplier : listSuppliers) {
                System.gc();

                List<Integer> list = listSupplier.get();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                    list.add(i);
                }
                long endTime = System.currentTimeMillis();

                System.out.println(list.getClass() + " | " +
                        size + " items | " +
                        (endTime - startTime) + " ms");
            }
        }
    }
}

package c4compile.bjc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Supplier;

public class AddLastItemToAListTest {
    @Test
    public void test() {
        List<Integer> sizes = Arrays.asList(1000000, 2000000, 5000000, 10000000);
        for (int size : sizes) {
            Map<String, Supplier<List<Integer>>> listSuppliers = new LinkedHashMap<>();
            listSuppliers.put("ArrayList", ArrayList::new);
            listSuppliers.put("ArrayList synchronized", () -> Collections.synchronizedList(new ArrayList<>()));
            listSuppliers.put("Arraylist with size", () -> new ArrayList<>(size));
            listSuppliers.put("LinkedList", LinkedList::new);
            listSuppliers.put("Stack", Stack::new);

            for (Map.Entry<String, Supplier<List<Integer>>> listSupplierEntry : listSuppliers.entrySet()) {
                System.gc();

                List<Integer> list = listSupplierEntry.getValue().get();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                    list.add(i);
                }
                long endTime = System.currentTimeMillis();

                System.out.println(listSupplierEntry.getKey() + " | " +
                        size + " items | " +
                        (endTime - startTime) + " ms");
            }
            System.out.println("---");
        }
    }
}

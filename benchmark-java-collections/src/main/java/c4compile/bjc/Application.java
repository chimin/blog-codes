package c4compile.bjc;

import java.util.*;
import java.util.function.Supplier;

public class Application {
    public static void main(String[] args) {
        System.out.println("Running benchmark...");

        List<Integer> sizes = Arrays.asList(1000000, 2000000, 5000000, 10000000);
        for (int size : sizes) {
            List<Supplier<List<Integer>>> listSuppliers = Arrays.asList(
                    ArrayList::new,
                    () -> new ArrayList<>(size),
                    LinkedList::new,
                    Stack::new
            );
            for (Supplier<List<Integer>> listSupplier : listSuppliers) {
                System.gc();
                long startTotalMemory = Runtime.getRuntime().totalMemory();

                List<Integer> list = listSupplier.get();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                    list.add(i);
                }
                long endTime = System.currentTimeMillis();

                System.gc();
                long endTotalMemory = Runtime.getRuntime().totalMemory();

                System.out.println(list.getClass() + " | " +
                        size + " items | " +
                        (endTime - startTime) + " ms | " +
                        (endTotalMemory - startTotalMemory) + " bytes");
            }
        }
    }
}

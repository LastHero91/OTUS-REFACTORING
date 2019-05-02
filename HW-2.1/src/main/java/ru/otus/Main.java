package ru.otus;

import java.util.function.Supplier;

public class Main {
    private static final int ARRAY_SIZE = 20_000_000;
    public static void main(String[] args) {
        System.out.println("int: " + measure(()->5));
        System.out.println("Integer.valueOf: " + measure(()->Integer.valueOf(5)));
        System.out.println("long: " + measure(()->5L));
        System.out.println("int[0]: " + measure(()->new int[0]));
        System.out.println("int[1]: " + measure(()->new int[1]));
        System.out.println("int[2]: " + measure(()->new int[2]));
        System.out.println("int[10]: " + measure(()->new int[10]));
        System.out.println("byte[0]: " + measure(()->new byte[0]));
        System.out.println("char[0]: " + measure(()->new char[0]));
        System.out.println("boolean[0]: " + measure(()->new boolean[0]));
        System.out.println("new String(new byte[0]): " + measure(()->new String(new byte[0])));
        System.out.println("new String(new char[0])): " + measure(()->new String(new char[0])));
        System.out.println("new String(\"\"): " + measure(()->new String("")));
        System.out.println("MyClass+int+long+boolean: " + measure(()->new MyClass()));
        System.out.println("MyClass+int: " + measure(()->new MyClass2()));
        System.out.println("MyClass+long: " + measure(()->new MyClass3()));
        System.out.println("MyClass+boolean: " + measure(()->new MyClass4()));
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

    }
    public static <T> long measure(Supplier<T> supplier) {
        try {
            Object array[] = new Object[ARRAY_SIZE];
            long size;
            long mem = getMem();
            for (int i = 0; i < array.length; i++) {
                array[i] = supplier.get();
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
    public static class MyClass{
        private int a = 4;
        private long l = 8L;
        private boolean b = true;
    }

    public static class MyClass2{
        private int a = 4;
    }

    public static class MyClass3{
        private long l = 8L;
    }

    public static class MyClass4{
        private boolean b = true;
    }
}

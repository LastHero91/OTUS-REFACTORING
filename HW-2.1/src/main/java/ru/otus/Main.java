package ru.otus;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    private static final int ARRAY_SIZE = 20_000_000;
    public static void main(String[] args) {
        System.out.println("int: " + measureInt(5));
        System.out.println("long: " + measureLong(5L));
        System.out.println("int[0]: " + measure(()->new int[0]));
        System.out.println("int[1]: " + measure(()->new int[1]));
        System.out.println("byte[0]: " + measure(()->new byte[0]));
        System.out.println("new String(new byte[0]): " + measure(()->new String(new byte[0])));
        System.out.println("new String(new char[0])): " + measure(()->new String(new char[0])));
        System.out.println("new String(\"\"): " + measure(()->new String("")));
        System.out.println("MyClass+int+long+boolean: " + measure(()->new MyClass()));
        System.out.println("MyClass+int: " + measure(()->new MyClass2()));
        System.out.println("MyClass+long: " + measure(()->new MyClass3()));
        System.out.println("MyClass+boolean: " + measure(()->new MyClass4()));
        System.out.println("ArrayList<Integer>: " + measure(()->new ArrayList<Integer>()));
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        System.out.println("***********************");
        System.out.println(VM.current().sizeOfField("boolean"));
        System.out.println(VM.current().sizeOfField("char"));
        System.out.println(VM.current().sizeOfField("int"));
        System.out.println(VM.current().sizeOfField("long"));
        System.out.println(ClassLayout.parseClass(MyClass.class).toPrintable());
        System.out.println('-');
        System.out.println(ClassLayout.parseClass(MyClass4.class).toPrintable());
        System.out.println('-');
        System.out.println(ClassLayout.parseClass(MyClass5.class).toPrintable());
        System.out.println('-');
        System.out.println(ClassLayout.parseClass(MyClass6.class).toPrintable());
        System.out.println('-');
        System.out.println(ClassLayout.parseClass(MyClass7.class).toPrintable());
        System.out.println("***********************");

    }

    private static String measureInt(int x){
        try {
            long mem = getMem();
            int array[] = new int[ARRAY_SIZE];
            long size;
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
            size = (getMem() - mem) / array.length;
            return String.valueOf(size);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return String.valueOf(-1);
    }

    private static String measureLong(long x){
        try {
            long mem = getMem();
            long array[] = new long[ARRAY_SIZE];
            long size;
            for (int i = 0; i < array.length; i++) {
                array[i] = x;
            }
            size = (getMem() - mem) / array.length;
            return String.valueOf(size);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return String.valueOf(-1);
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
    public static class MyClass5{
        private boolean b = true;
        private boolean boo = true;
    }
    public static class MyClass6{
        private boolean b = true;
        private int i = 5;
        private boolean boo = true;
    }
    public static class MyClass7{
        private boolean b = true;
        private int i = 5;
    }
}

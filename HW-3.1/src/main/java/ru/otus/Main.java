package ru.otus;

import ru.otus.utils.MyArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer array[] = new Integer[3];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        List<Integer> list = new ArrayList<>();
        List<Integer> myList = new MyArrayList<>();

        Collections.addAll(list, array);
        System.out.println(list);

        Collections.addAll(myList, array);
        System.out.println(myList);

        list.add(0);
        System.out.println(list);
        myList.add(5);
        System.out.println(myList);

        Collections.copy(myList, list);
        System.out.println(myList);

        Collections.sort(myList, Integer::compareTo);
        System.out.println(myList);
    }
}

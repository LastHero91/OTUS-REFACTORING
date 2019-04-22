package ru.otus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Main {
    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("Multi");
        multiset.add("Multi");
        multiset.add("Set");
        multiset.add("Example");
        multiset.add("Example");
        multiset.add("Example");

        System.out.println(multiset);
    }
}

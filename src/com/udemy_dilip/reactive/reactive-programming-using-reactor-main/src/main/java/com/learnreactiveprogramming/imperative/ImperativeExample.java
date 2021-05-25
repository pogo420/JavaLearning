package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeExample {

    public static void main(String[] args) {
        List<String> raw_data = List.of("ola", "boom", "lloko", "kiko", "a", "b", "boom");
        System.out.println(valuesGreaterThanN(raw_data, 3));
        System.out.println(valuesGreaterThanNFunc(raw_data, 3));
    }

    private static List<String> valuesGreaterThanNFunc(List<String> raw_data, int i) {
        List<String> filtered_list = raw_data.stream()
                .filter(value -> value.length() > 3)
                .distinct()
                .collect(Collectors.toList());
        return filtered_list;
    }

    private static List<String>  valuesGreaterThanN(List<String> raw_data, int i) {
        List<String> filtered_list = new ArrayList<>();
        for(String value: raw_data){
            if ((value.length() > 3) && (!filtered_list.contains(value))) {
                filtered_list.add(value);
            }
        }
        return filtered_list;
    }
}

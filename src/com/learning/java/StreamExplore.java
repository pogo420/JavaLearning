package com.learning.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExplore {

    public static void main(String[] args) {

        List<Integer> a = Arrays.asList(1,4,5,6,8,9);
        List<Integer> out;

        a.stream().map(x->x+1).forEach(x-> System.out.println(x));
        out = a.stream().map(x->x+2).filter(x->x%2!=0).collect(Collectors.toList());
        System.out.println(out);
    }
}

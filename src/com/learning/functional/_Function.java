package com.learning.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class _Function {
    public static void main(String[] args) {
        int increment = inc(0);


        System.out.println(increment);

        System.out.println(incFunc.apply(0));

        pf.accept(123);

        System.out.println(evenCheck.and(tenCheck).test(1302));

        System.out.println(getUrl.get());
    }

    // functional approach
    static Function<Integer, Integer> incFunc = x -> x+1;

    static Consumer<Integer> pf = System.out::println;

    static Predicate<Integer> evenCheck = x -> x%2==0;

    static Predicate<Integer> tenCheck = x -> x%10==0;

    static Supplier<String> getUrl = () -> "http:www.google.com";

    private static int inc(int val) {
        return val + 1;
    }
}

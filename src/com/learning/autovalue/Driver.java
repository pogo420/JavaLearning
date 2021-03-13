package com.learning.autovalue;

public class Driver {

    public static void main(String[] args) {
        AutoCheck person1 = AutoCheck.builder().setId(32).setName("Puchu").build();
        AutoCheck person2 = AutoCheck.builder().setId(32).setName("Puchu").build();
        AutoCheck person3 = AutoCheck.builder().setId(32).setName("Puchu1").build();

        System.out.println(person1.equals(person2));
        System.out.println(person1.equals(person3));

    }
}

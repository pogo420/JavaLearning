package com.learning.functional;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Person> persons = List.of(
                new Person("Johm", Gender.MALE),
                new Person("Sud", Gender.FEMALE),
                new Person("Arnab", Gender.MALE),
                new Person("Kolo", Gender.FEMALE)
        );

        // imperative approach
        for (Person i : persons) {
            if (i.getGender() == Gender.FEMALE) {
                System.out.println(i.toString());
            }
        }
        // functional approach
        persons.stream()
                .filter(person -> person.getGender() ==Gender.FEMALE)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}

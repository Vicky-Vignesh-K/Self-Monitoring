package com.self.monitoring.SelfMonitoring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PracticePrograms {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,10,7};

        Arrays.stream(arr)
                .filter(num -> num%2==0)
                .forEach(System.out::println);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(40);
        numbers.add(30);
        numbers.add(10);

        System.out.println(numbers.stream().count());

        numbers
                .stream()
                .sorted((num1,num2)-> num1>num2?1:-1)
//                .distinct()
                .forEach(System.out::println);
        System.out.println();
        numbers
                .stream()
                .sorted((num1,num2)-> num2-num1)
//                .distinct()
                .forEach(System.out::println);


        System.out.println();
        List<String> names = new ArrayList<>();
        names.add("Tharun");
        names.add("Arun");
        names.add("Alen");

        names
                .stream()
//                .sorted(Comparator.naturalOrder())// Ascending order or use below method
                .sorted(String::compareTo)
                .forEach(System.out::println);

        System.out.println();
        names
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();
        names
                .stream()
                .sorted(Comparator.naturalOrder())
                .map(name -> name.toUpperCase())
//                .forEach(name -> System.out.println(name.toUpperCase()));
                .forEach(System.out::println);

        System.out.println();
        names
                .stream()
                .sorted(Comparator.naturalOrder())
                .map(name -> name.charAt(0))
//                .forEach(name -> System.out.println(name.toUpperCase()));
                .forEach(System.out::println);

        System.out.println();
        Arrays.stream(arr)
                .map(num -> num/2)
                .forEach(System.out::println);

    }
}

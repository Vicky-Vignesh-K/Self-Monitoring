package com.self.monitoring.SelfMonitoring.controller;

import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee("Arun",2,2,50000d);
        Employee employee2 = new Employee("Tharun",5,3,70000d);
        Employee employee3 = new Employee("Varun",10,4,80000d);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employees.stream().forEach(System.out::println);

        System.out.println();

//        employees.stream()
//                .filter(emp -> emp.getSalary()>50000d)
//                .forEach(System.out::println);

        employees.stream()
                .filter(emp -> emp.getSalary()>50000d)
                .map(emp -> emp.getEmpName())
                .forEach(System.out::println);

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);

        System.out.println();
        nums.stream()
                .limit(2)
                .forEach(System.out::println);

        System.out.println();

        nums.stream()
                .skip(2)
                .forEach(System.out::println);

        System.out.println();

        nums.stream()
                .limit(3)
                .skip(2)
                .forEach(System.out::println);

        System.out.println();

        Optional<Integer> additionOfList = nums.stream()
                .reduce((num1,num2)->num1+num2);
        System.out.println(additionOfList.get());

        System.out.println();
        // parallel stream does not preserve order because it runs on different threads if we want order we can use for each order
        nums.parallelStream()
                .forEach(System.out::println);

        System.out.println();
        nums.parallelStream()
                .forEachOrdered(System.out::println);

        System.out.println("---------------------------------------------------");

        HashMap<String,Integer> mp = new HashMap<>();
        mp.put("Arun", 100);
        mp.put("Tharun", 300);
        mp.put("varun", 200);
        mp.put("Balaji", 99);

        mp.keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();
        mp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);

        System.out.println();
        //converting map into list or set
       List<String> names = mp.keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(names);

        System.out.println();
        Set<String> setOfNames = mp.keySet()
                .stream()
                .collect(Collectors.toSet());
        System.out.println(setOfNames);

        System.out.println();
        boolean res = setOfNames.stream()
                .anyMatch(name -> name.endsWith("n"));
        System.out.println(res);

        System.out.println();
         res = setOfNames.stream()
                .anyMatch(name -> name.endsWith("n")&& name.startsWith("V"));
        System.out.println(res);


       List<String> namesListWithA =  setOfNames.stream()
                .filter(name -> name.endsWith("n"))
                .collect(Collectors.toList());

        System.out.println(namesListWithA);

        List<Map.Entry<String,Integer>> l = mp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        System.out.println(l);

    }
}

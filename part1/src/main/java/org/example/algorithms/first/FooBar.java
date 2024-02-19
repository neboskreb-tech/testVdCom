package org.example.algorithms.first;


import java.util.stream.IntStream;

public class FooBar {

     public  static  void printFooBar(int n){
         for (int i = 1; i <= n; i++) {
            StringBuilder output = new StringBuilder();
             if(i % 3==0){
                 output.append("Foo");
             }
             if(i % 5==0){
                 output.append("Bar");
             }
             if(output.length()==0){
                 output.append(i);
             }
             System.out.println(output.toString());
         }
     }

    public  static  void printFooBarTwo(int n){
        IntStream.rangeClosed(1, n).mapToObj(i -> i % 3 == 0 ? (i % 5 == 0 ? "FooBar" : "Foo") : (i % 5 == 0 ? "Bar" : String.valueOf(i))).forEach(System.out::println);
    }
    public  static  void printFooBarHeadOn(int n){
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FooBar");
            } else if (i % 3 == 0) {
                System.out.println("Foo");
            } else if (i % 5 == 0) {
                System.out.println("Bar");
            } else {
                System.out.println(i);
            }
        }
    }
    public static void recursiveFooBar(int current, int n){
        if (current > n) {
            return;
        }

        if (current % 3 == 0 && current % 5 == 0) {
            System.out.println("FooBar");
        } else if (current % 3 == 0) {
            System.out.println("Foo");
        } else if (current % 5 == 0) {
            System.out.println("Bar");
        } else {
            System.out.println(current);
        }
        recursiveFooBar(current + 1, n);
    }

}

package com.arobs.generics;

public class GenericMain<T> {

    public static void main(String[] args) {

        //Test GenericBox
        GenericBox<String> stringGenericBox = new GenericBox<String>("This is a string");
        GenericBox<Integer> integerGenericBox = new GenericBox<Integer>(234);

        System.out.println(stringGenericBox.getContent());
        System.out.println(integerGenericBox.getContent());
    }
}

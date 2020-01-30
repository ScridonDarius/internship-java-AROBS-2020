package com.arobs.threads;

public class Launcher {

    public static void main(String[] args) {

        TestSleepMethod thread1 = new TestSleepMethod();
        TestSleepMethod thread2 = new TestSleepMethod();

        thread1.start();
        thread2.start();

    }
}

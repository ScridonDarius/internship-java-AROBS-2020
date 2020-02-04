package com.arobs.threads.sleep;

public class TestSleepMethod extends Thread {

    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(getName() + " Value : " + i);
        }
    }
}

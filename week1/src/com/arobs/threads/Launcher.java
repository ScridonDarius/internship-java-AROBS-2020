package com.arobs.threads;

import com.arobs.threads.sleep.TestSleepMethod;
import com.arobs.threads.wait.notify.Message;
import com.arobs.threads.wait.notify.Notifier;
import com.arobs.threads.wait.notify.Waiter;

public class Launcher {

    public static void main(String[] args) {

        /**
         * TestSleepMethod from package sleep -- test
         */

      /*  TestSleepMethod thread1 = new TestSleepMethod();
        TestSleepMethod thread2 = new TestSleepMethod();

        thread1.start();
        thread2.start();
       */

        /**
         * Test wait-notify
         */

        Message msg = new Message("Hello World!");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter,"waiter").start();

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
    }
}

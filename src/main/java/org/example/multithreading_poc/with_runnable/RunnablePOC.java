package org.example.multithreading_poc.with_runnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class Monkey1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Monkey 1 : Eating Banana " + (i+1));
            System.out.println("Monkey 1 threadName " + Thread.currentThread());
        }
    }
}

class Monkey2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Monkey 2 : Eating Banana " + (i+1));
            System.out.println("Monkey 2 threadName " + Thread.currentThread());

        }
    }
}

public class RunnablePOC {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Monkey1());
        Thread t2 = new Thread(new Monkey2());
        System.out.println("Main thread name " + Thread.currentThread());
        t1.start();
        t2.start();
        System.out.println("Main thread name " + Thread.currentThread());

    }

}

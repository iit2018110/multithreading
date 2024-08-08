package org.example.multithreading_poc.with_thread;

class Monkey1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Monkey 1 : Eating Banana " + (i+1));
        }
    }
}

class Monkey2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Monkey 2 : Eating Banana " + (i+1));
        }
    }
}


public class ThreadPOC {
    public static void main(String[] args) {
        Thread t1 = new Monkey1();
        Thread t2 = new Monkey2();

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();

        System.out.println("Last line in main method.");
    }
}

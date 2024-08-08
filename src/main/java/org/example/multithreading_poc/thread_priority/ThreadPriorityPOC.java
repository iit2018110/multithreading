package org.example.multithreading_poc.thread_priority;

class Worker1 extends Thread {
    @Override
    public void run(){
        System.out.println("Inside worker1");
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Worker1 loopCount is " + i);
//        }
    }
}

class Worker2 extends Thread {
    @Override
    public void run(){
        System.out.println("Inside worker2");
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Worker2 loopCount is " + i);
//        }
    }
}

public class ThreadPriorityPOC {
    public static void main(String[] args) {
        System.out.println("Default priority of Thread is : " + Thread.currentThread().getPriority());
        System.out.println("Min priority of Thread is : " + Thread.MIN_PRIORITY);
        System.out.println("Max priority of Thread is : " + Thread.MAX_PRIORITY);

//        Thread.currentThread().setPriority(8);
//        System.out.println("Priority of main thread changes to " + Thread.currentThread().getPriority());

        Thread t1 = new Thread(new Worker1());
        t1.setPriority(Thread.MAX_PRIORITY);

        Thread t2 = new Thread(new Worker2());
        t2.setPriority(Thread.MIN_PRIORITY);

        t2.start();
        t1.start();

        System.out.println("Main thread executing...");
    }
}

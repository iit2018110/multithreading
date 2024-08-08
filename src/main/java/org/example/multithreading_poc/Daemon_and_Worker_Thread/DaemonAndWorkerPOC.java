package org.example.multithreading_poc.Daemon_and_Worker_Thread;

class DaemonThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Daemon thread Name is : " + Thread.currentThread().getName());
        System.out.println("Daemon thread Id is : " + Thread.currentThread().getId());

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello World from a Daemon Thread");
        }
    }
}

class WorkerThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Worker thread Name is : " + Thread.currentThread().getName());
        System.out.println("Worker thread Id is : " + Thread.currentThread().getId());

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Hello World from Worker Thread loopCount : " + i);
        }
    }
}

public class DaemonAndWorkerPOC {
    public static void main(String[] args) {
        long currentThreadId = Thread.currentThread().getId();
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("main thread Name is : " + currentThreadName);
        System.out.println("main thread Id is : " + currentThreadId);

        Thread t1 = new Thread(new DaemonThread());
        Thread t2 = new Thread(new WorkerThread());

        t1.setDaemon(true);
        System.out.println(t1.isDaemon());

        t1.start();
        t2.start();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("End of main() method.");
    }
}

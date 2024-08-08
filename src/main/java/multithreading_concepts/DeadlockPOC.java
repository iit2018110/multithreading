package multithreading_concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Process {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    public void worker1() {
        lock1.lock();
        System.out.println("worker1 acquired lock1");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock2.lock();
        System.out.println("worker1 acquired lock2");

        lock1.unlock();
        lock2.unlock();

        System.out.println("worker1 released locks.");
    }

    public void worker2() {
        lock2.lock();
        System.out.println("worker2 acquired lock2");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock1.lock();
        System.out.println("worker2 acquired lock1");

        lock1.unlock();
        lock2.unlock();

        System.out.println("worker2 released locks.");
    }
}

public class DeadlockPOC {
    public static void main(String[] args) {
        Process process = new Process();

        new Thread(process::worker1, "worker1").start();
        new Thread(process::worker2, "worker2").start();
    }
}

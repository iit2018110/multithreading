package multithreading_concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class App {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void worker1() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("worker1 acquired lock1");

            System.out.println("worker1 trying to acquire lock2");

            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println("worker1 acquired lock2");
                    break;
                } else {
                    System.out.println("worker1 not able to acquired lock2");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("worker2 acquired lock2");

            System.out.println("worker2 trying to acquire lock1");

            try {
                if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println("worker2 acquired lock1");
                    break;
                } else {
                    System.out.println("worker2 not able to acquired lock1");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lock1.unlock();
        lock2.unlock();
    }
}

public class LiveLockPOC {
    public static void main(String[] args) {
        App app = new App();
        new Thread(app::worker1, "worker1").start();
        new Thread(app::worker2, "worker2").start();
    }
}

package interthread_communication.lock_with_custom_objects;

public class CustomObjectLockPOC {
    static int counter1 = 0;
    static int counter2 = 0;

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void incrementCounter1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    public static void incrementCounter2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    incrementCounter1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    incrementCounter2();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finally value of counter1 is " + counter1);
        System.out.println("Finally value of counter2 is " + counter2);
    }

    public static void main(String[] args) {
        process();
    }
}

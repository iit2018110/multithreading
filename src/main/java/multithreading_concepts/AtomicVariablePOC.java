package multithreading_concepts;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerPOC {
    public static AtomicInteger counter = new AtomicInteger(0);
    public void increment() {
        for (int i = 0; i < 10000; i++) {
            counter.getAndIncrement();
        }
    }
}

public class AtomicVariablePOC {
    public static void main(String[] args) {
        AtomicIntegerPOC obj = new AtomicIntegerPOC();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.increment();
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

        System.out.println("counter is " + AtomicIntegerPOC.counter);
    }
}

package interthread_communication.producer_and_consumer;

import java.util.ArrayList;
import java.util.List;

class Processor {
    List<Integer> list = new ArrayList<>();

    private final Object lock = new Object();
    private static final int MAX_LIMIT = 5;
    private static final int MIN_LIMIT = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if(list.size() == MAX_LIMIT) {
                    lock.notify(); // notify() is going to wake up other threads if and only if they are in waiting state.
                    lock.wait(); // release intrinsic lock.
                }

                if (list.size() < MAX_LIMIT) {
                    list.add(list.size() + 1);
                    System.out.println("List is " + list);
                }
                Thread.sleep(100);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                System.out.println("Inside consumer");
                if (list.size() > MIN_LIMIT) {
                    Thread.sleep(100);
                    list.remove(list.size() - 1);
                    System.out.println("List is : " + list);
                }

                if(list.size() == MIN_LIMIT) {
                    lock.notify();
                    lock.wait();
                }

                Thread.sleep(100);
            }
        }
    }
}

public class ProducerAndConsumerPOC {
    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}

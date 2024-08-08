package interthread_communication.synchronization;

public class SynchronizationPOC {
    static int counter1 = 0;
    static int counter2 = 0;

    // This is Object Level Locking

    // can only run one method at a time because synchronized method use intrinsic lock of object.
    // And every object has a single intrinsic/monitor lock


//    public static synchronized void incrementCounter1() {
//        counter1++;
//    }
//
//    usually it's not a good programming practice to use synchronized keyword
//    public static synchronized void incrementCounter2() {
//        counter2++;
//    }


//    -----------------------------------------------------
    public static void incrementCounter1() {
//      this is class level locking but again will acquire intrinsic lock and eventually can run only one method at a time
//      rule of thumb: we synchronize blocks that are 100% necessary
        synchronized(SynchronizationPOC.class) {
            counter1++;
        }
    }

    public static synchronized void incrementCounter2() {
        synchronized(SynchronizationPOC.class) {
            counter2++;
        }
    }


    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
//                    counter++;
                    incrementCounter1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
//                    counter++;
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

        System.out.println("Finally value of counter1 is : " + counter1);
        System.out.println("Finally value of counter2 is : " + counter2);

    }

    public static void main(String[] args) {
        process();
    }
}

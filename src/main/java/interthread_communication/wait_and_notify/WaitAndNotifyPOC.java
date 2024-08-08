package interthread_communication.wait_and_notify;

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running produce method...");
//            Thread.sleep(8000);
            wait();
            System.out.println("Again running produce method...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume method is executing...");
            notify();
            // it's not going to handover lock immediately : we can make further operations
            Thread.sleep(5000);
        }
    }
}

public class WaitAndNotifyPOC {
    public static void main(String[] args) {
        Process process = new Process();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}

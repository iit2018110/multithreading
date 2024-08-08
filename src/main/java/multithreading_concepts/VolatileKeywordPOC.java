package multithreading_concepts;

class Worker implements Runnable {
    // it will stored in the main memory
    // 1.) Variables may be stored in main memory without even volatile keyword.
    // 2.) Both threads may use same cache!!
    private volatile boolean terminated = false;

    @Override
    public void run() {
        while (!isTerminated()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("worker is running ...");
        }
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public boolean isTerminated() {
        return terminated;
    }
}

public class VolatileKeywordPOC {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);

        t1.start();
        Thread.sleep(2000);
        worker.setTerminated(true);
    }
}

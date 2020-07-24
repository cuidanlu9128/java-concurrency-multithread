package demo1;

public class InterruptDemo0 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // STEP 2: When the thread is started, our iteration begins
                for (int i = 0; i < 999999; i++) {
                    // STEP 3: check if our thread is interrupted. If so, break the loop and step out
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + i + " is running");
                }
            }
        });

        // STEP 1: START Thread
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // STEP 4: if we mark the thread interrupted, will this terminate our infinite loop?
        t1.interrupt();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t1.getState());
    }
}

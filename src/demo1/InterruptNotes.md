### Thread Interrupt

Thread Interrupt is a way that multiple threads can use to work and communicate with 
each other, by marking interruption flags. Thread will use this flag to decide if this 
thread will be interrupted or not, which means, if you mark the thread’s flag to be 
interrupted, but the thread chooses not to use this flag, 
the thread will still work whatsoever.

We will discuss three methods

```$Java
public void interrupt()
public boolean isInterrupted()
public static boolean interrupted()
```

In this lecture, we only discuss public methods and omit all private methods in the thread class.

Let's take a look at the following code

```$Java
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
```

To fully understand how interruption work in java, let's first take a look at the Thread.currentThread().isInterrupted() method in the Thread source code.

```$Java
    public boolean isInterrupted() {
        return isInterrupted(false);
    }
```

The ```isInterrupted()``` method will return a boolean result from the ```isInterrupted(false)``` method, which is a native method.
Here is the source code of this method.

```$Java
private native boolean isInterrupted(boolean ClearInterrupted);
```

This method is used to return the current thread's interruption flag. If it returns true, then the current thread's interruption flag is true.
The parameter of this method is used to determine if we want to reset the interruption flag. If we pass ```ClearInterrupted = true```, it means we reset the 
interruption state back to **false**, meaning our current thread's interruption flag is false, aka our current thread is not being interrupted.
 


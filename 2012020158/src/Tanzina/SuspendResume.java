package Tanzina;

class NewThreadFive implements Runnable {
    String name; // name of thread
    Thread t;
    boolean suspendFlag;
    NewThreadFive(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
        t.start(); // Start the thread
    }
    // This is the entry point for thread.
    public void run() {
        try {
            for(int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized(this) {
                    while(suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
        System.out.println(name + " exiting.");
    }
    synchronized void mysuspend() {
        suspendFlag = true;
    }
    synchronized void myresume() {
        suspendFlag = false;
        notify();
    }
}
public class SuspendResume {
    public static void main(String args[]) {
        NewThreadFive ob1 = new NewThreadFive("Tanzina");
        NewThreadFive ob2 = new NewThreadFive("Shammi");
        try {
            Thread.sleep(1000);
            ob1.mysuspend();
            System.out.println("Suspending thread Tanzina");
            Thread.sleep(1000);
            ob1.myresume();
            System.out.println("Resuming thread Tanzina");
            ob2.mysuspend();
            System.out.println("Suspending thread Shammi");
            Thread.sleep(1000);
            ob2.myresume();
            System.out.println("Resuming thread Shammi");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        // wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}

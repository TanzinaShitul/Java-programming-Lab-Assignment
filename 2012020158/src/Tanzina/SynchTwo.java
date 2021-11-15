package Tanzina;

class CallmeTwo {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class CallerTwo implements Runnable {
    String msg;
    CallmeTwo target;
    Thread t;

    public CallerTwo(CallmeTwo targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    // synchronize calls to call()
    public void run() {
        synchronized (target) { // synchronized block
            target.call(msg);
        }
    }
}

public class SynchTwo {
    public static void main(String args[]) {
        CallmeTwo target = new CallmeTwo();
        CallerTwo ob1 = new CallerTwo(target, "Hello");
        CallerTwo ob2 = new CallerTwo(target, "Synchronized");
        CallerTwo ob3 = new CallerTwo(target, "World");
        // wait for threads to end
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

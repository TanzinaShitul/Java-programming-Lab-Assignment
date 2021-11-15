package Tanzina;

class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.print("]");
    }
}

class Caller implements Runnable {
    String msg;
    CallmeTwo target;
    Thread t;

    public Caller(CallmeTwo targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        target.call(msg);
    }
}

public class Synch {
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

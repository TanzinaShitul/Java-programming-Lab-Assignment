package Tanzina;

class QTwo {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        while (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class ProducerTwo implements Runnable {
    QTwo q;

    ProducerTwo(QTwo q) {
        this.q = q;
        new Thread(this, "ProducerTwo").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

class ConsumerTwo implements Runnable {
    QTwo q;

    ConsumerTwo(QTwo q) {
        this.q = q;
        new Thread(this, "ConsumerTwo").start();
    }

    public void run() {
        while (true) {
            q.get();
        }
    }
}

public class PCFixed {
    public static void main(String args[]) {
        QTwo q = new QTwo();
        new ProducerTwo(q);
        new ConsumerTwo(q);
        System.out.println("Press Control-C to stop.");
    }
}

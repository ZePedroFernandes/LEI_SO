
import java.util.concurrent.*;

public class Exerc2 implements Runnable {

    Semaphore s1, s2;

    public Exerc2(Semaphore sem1, Semaphore sem2) {
        s1 = sem1;
        s2 = sem2;
    }

    public void run() {
        boolean flag = true;

        try {
            String myname = Thread.currentThread().getName();

            while (flag) {
                s1.acquire();
                if (s2.availablePermits() > 0) {
                    System.out.println("[" + myname + "] : linha " + s2.availablePermits());
                    s2.acquire();
                } else {
                    flag = false;
                }

                s1.release();
                Thread.sleep(1000);
            }
        } catch (InterruptedException iex) {
        }
    }

    public static void main(String args[]) {
        final Semaphore sem1 = new Semaphore(1);
        final Semaphore sem2 = new Semaphore(10);

        Thread[] ths = new Thread[20];

        for (int i = 0; i < 20; i++) {
            ths[i] = new Thread(new Exerc2(sem1, sem2), "Th" + i);
        }

        for (int i = 0; i < 20; i++) {
            ths[i].start();
        }

        try {
            for (int i = 0; i < 20; i++) {
                ths[i].join();
            }
        } catch (InterruptedException e) {;
        }
    }
}

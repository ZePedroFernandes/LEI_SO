
import java.util.Random;
import java.util.concurrent.*;

public class Exerc3 {

    public static class Th1 implements Runnable {

        final private int MAX = 10;
        private Semaphore sem;

        public Th1(Semaphore sem) {
            this.sem = sem;
        }

        public void run() {
            try {
                Random gerador = new Random();
                int num_aleatorio = gerador.nextInt(MAX);
                String myname = Thread.currentThread().getName();

                System.out.println("[" + myname + "]: " + "INIT (" + num_aleatorio + ")");
                Thread.sleep(num_aleatorio * 1000);
                System.out.println("[" + myname + "]: " + "END");

                sem.release();  // permite que o Th2 avance
            } catch (InterruptedException iex) {
            }
        }
    }

    public static class Th2 implements Runnable {

        final private int MAX = 10;
        private Semaphore sem;

        public Th2(Semaphore sem) {
            this.sem = sem;
        }

        public void run() {
            try {
                sem.acquire();  // aguarda que Th1 termine

                Random gerador = new Random();
                String myname = Thread.currentThread().getName();

                System.out.println("[" + myname + "]: " + gerador.nextInt(MAX));
            } catch (InterruptedException iex) {
            }
        }
    }

    public static void main(String args[]) {
        final Semaphore sem = new Semaphore(0);

        Thread th1 = new Thread(new Th1(sem), "Th1");
        Thread th2 = new Thread(new Th2(sem), "Th2");

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
        }
    }
}

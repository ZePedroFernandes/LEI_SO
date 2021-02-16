
import java.util.concurrent.Semaphore;

public class Exerc2 {

    public static class Printer implements Runnable {

        Semaphore sem;
        Semaphore mutex;

        public Printer(Semaphore sem, Semaphore mutex) {
            this.sem = sem;
            this.mutex = mutex;
        }

        private void print() {
            try {
                mutex.acquire();
                if (sem.availablePermits() > 0) {
                    sem.acquire();
                    System.out.println("Thread [" + Thread.currentThread().getName() + "] line");
                }
                mutex.release();
            } catch (InterruptedException e) {
            }
        }

        @Override
        public void run() {
            try {
                print();
                Thread.sleep(1000);
                print();
            } catch (InterruptedException e) {
            }
        }

    }

    public static void main(String[] args) {

        Semaphore sem1 = new Semaphore(10);
        Semaphore mutex = new Semaphore(1);

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Printer(sem1, mutex), "Th" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (int i = 0; i < 20; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
        }

    }
}

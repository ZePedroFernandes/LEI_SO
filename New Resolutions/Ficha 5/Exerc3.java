
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Exerc3 {

    public static class NumberPrinter implements Runnable {

        Semaphore sem;

        public NumberPrinter(Semaphore sem) {
            this.sem = sem;
        }

        @Override
        public void run() {
            System.out.println("Init");
            try {
                Thread.sleep(new Random().nextInt(9) * 1000 + 1000);
            } catch (InterruptedException e) {
            }

            System.out.println("End");
            sem.release();
        }

    }

    public static class LinePrinter implements Runnable {

        Semaphore sem;

        public LinePrinter(Semaphore sem) {
            this.sem = sem;
        }

        @Override
        public void run() {
            try {
                sem.acquire();
                int random = 1 + new Random().nextInt(8);
                for (int i = 0; i < random; i++) {
                    System.out.println("Line " + i);
                }
                sem.release();
            } catch (InterruptedException ex) {
            }

        }
    }

    public static void main(String[] args) {

        Semaphore sem1 = new Semaphore(0);
        
        Thread th1 = new Thread(new NumberPrinter(sem1));
        Thread th2 = new Thread(new LinePrinter(sem1));
        
        th1.start();
        th2.start();
        
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
        }

    }
}

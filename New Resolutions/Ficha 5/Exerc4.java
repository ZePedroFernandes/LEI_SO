
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Exerc4 {

    public static class Printer implements Runnable {

        Semaphore sem;

        public Printer(Semaphore sem) {
            this.sem = sem;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();

            while (true) {
                try {
                    sem.acquire();
                    System.out.println(name);
                    Thread.sleep(1000);
                    sem.release();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[20];
        Semaphore[] sems = new Semaphore[20];

        for (int i = 0; i < threads.length; i++) {
            sems[i] = new Semaphore(0, true);
            threads[i] = new Thread(new Printer(sems[i]), "Th" + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }

        Scanner keyboard = new Scanner(System.in);
        int number, last = -1;

        while (true) {
            do {
                number = keyboard.nextInt();
                keyboard.nextLine();
            } while (number > 19 || last == number);

            if (last >= 0) {
                System.out.println("A tentar parar o Th" + last);
                try {
                    sems[last].acquire();
                } catch (InterruptedException iex) {
                }
            }

            if (number >= 0) {
                System.out.println("Start thread " + number);
                sems[number].release();
                last = number;
            }else{
                System.exit(0);
            }

        }
    }
}

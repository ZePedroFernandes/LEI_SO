package p1ex_4;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class P1EX_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int thNumber = 5;
        Semaphore[] sems = new Semaphore[thNumber];
        Thread[] threads = new Thread[thNumber];
        for (int i = 0; i < thNumber; i++) {
            sems[i] = new Semaphore(0);
            threads[i] = new Thread(new PrinterTh(sems[i]), "Th" + i);
        }

        for (Thread th : threads) {
            th.start();
        }
        System.out.println("[MAIN] All threads ready");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("[MAIN] Choose a thread");

        int number = Integer.parseInt(keyboard.nextLine());
        int last = number;

        boolean flag = true;

        if (number >= 0 && number < thNumber) {
            sems[number].release();
            while (flag) {
                number = Integer.parseInt(keyboard.nextLine());
                System.out.println("Number read successfully");
                if (number < 0 || number >= thNumber) {
                    flag = false;
                } else {
                    try {
                        System.out.println("[MAIN] Attempting to stop thread " + last);
                        sems[last].acquire();
//                        sems[last].drainPermits();
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Realising thread " + number);
                    sems[number].release();
                    last = number;
                }
            }
        }

        System.out.println("Shutting down");
        for (Thread th : threads) {
            th.interrupt();
        }
        System.out.println("Shutted down");
    }

}

class PrinterTh implements Runnable {

    Semaphore s;

    public PrinterTh(Semaphore sem) {
        s = sem;
    }

    @Override
    public void run() {
        try {
            String myname = Thread.currentThread().getName();
            System.out.println("[" + myname + "] I'm here.");
            while (true) {
                s.acquire();
//                s.drainPermits();
                System.out.println("[" + myname + "]");
                s.release();
                Thread.sleep(1000);
            }
        } catch (InterruptedException iex) {
        }
    }
}

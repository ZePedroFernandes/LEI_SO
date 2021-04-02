
import java.util.concurrent.*;
import java.util.Scanner;

public class Exerc4 implements Runnable {

    Semaphore s;

    public Exerc4(Semaphore sem) {
        s = sem;
    }

    public void run() {
        try {
            String myname = Thread.currentThread().getName();
            System.out.println("[" + myname + "] I'm here.");
            while (true) {
                s.acquire();
                s.drainPermits();
                System.out.println("[" + myname + "] " + (s.availablePermits() + 1));
                Thread.sleep(1000);
                s.release();
            }
        } catch (InterruptedException iex) {
        }
    }

    public static void main(String args[]) {
        final int MAX = 5;
        final Semaphore[] sems = new Semaphore[MAX];
        Thread[] ths = new Thread[MAX];
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < ths.length; i++) {
            sems[i] = new Semaphore(0, true);
            ths[i] = new Thread(new Exerc4(sems[i]), "Th" + i);
        }
        for (int i = 0; i < ths.length; i++) {
            ths[i].start();
        }

        System.out.println("[Main] Threads criados!");
        boolean flag = true;
        int last = -1;

        while (flag) {
            System.out.println("[Main] Thread a acordar?:");
            int temp = Integer.parseInt(keyboard.nextLine());

            if (last >= 0) {
                System.out.println("A tentar parar o Th" + last);
                try {
                    sems[last].acquire();
                    sems[last].drainPermits();
                } catch (InterruptedException iex) {
                }
            }

            if (temp >= 0 && temp < MAX && sems[temp].availablePermits() <= 0) {
                sems[temp].release();
                System.out.println("[Main] Released thread number " + temp);
                System.out.println("[Main] Thread number " + temp + " with " + sems[temp].availablePermits() + " permits");

            }

            last = temp;

            if (temp >= MAX) {
                flag = false;
                System.out.println("[Main] quiting!");
                for (int i = 0; i < ths.length; i++) {
                    ths[i].interrupt();
                }
            }
        }
    }
}

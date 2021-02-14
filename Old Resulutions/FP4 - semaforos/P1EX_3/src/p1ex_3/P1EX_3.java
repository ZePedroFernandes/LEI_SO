package p1ex_3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class P1EX_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        Thread t1 = new Thread(new Thread1(sem));
        Thread t2 = new Thread(new Thread2(sem));

        try {
            sem.acquire();
            t1.start();
            sem.acquire();
            t2.start();
        } catch (InterruptedException ex) {
            Logger.getLogger(P1EX_3.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(P1EX_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

class Thread1 implements Runnable {

    Semaphore sem;

    public Thread1(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            System.out.println("Init");
            int sleep = random.nextInt(8) + 1;
            System.out.println(sleep);
            Thread.currentThread().sleep(sleep * 1000);
            System.out.println("End");
            sem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(P1EX_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class Thread2 implements Runnable {

    Semaphore sem;

    public Thread2(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        Random random = new Random();
        int lines = random.nextInt(9 - 1) + 1;
        System.out.println(lines);
        for (int i = 0; i < lines; i++) {
            System.out.println("Linha:" + i);
        }
        sem.release();
    }

}

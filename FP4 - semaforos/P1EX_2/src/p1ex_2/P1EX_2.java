/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1ex_2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class P1EX_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(10);

        Thread[] threads = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Current(sem1, sem2, i), "Th" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {

            }
        }

    }

}

class Current implements Runnable {

    Semaphore sem1, sem2;

    int n;

    public Current(Semaphore sem1, Semaphore sem2, int n) {
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.n = n;
    }

    @Override
    public void run() {
        boolean flag = false;
        while (true) {
            try {
                sem1.acquire();
                if (sem2.availablePermits() > 0) {
                    sem2.acquire();
                    System.out.println("Linha :" + this.n + "->" + Thread.currentThread().getName());

                } else {
                    sem1.release();
                    break;
                }
                sem1.release();
                Thread.currentThread().sleep(1000);

            } catch (InterruptedException e) {
            }
        }

    }
}

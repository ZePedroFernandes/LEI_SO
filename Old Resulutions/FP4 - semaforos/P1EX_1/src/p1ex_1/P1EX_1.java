/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1ex_1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class P1EX_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        Semaphore start = new Semaphore(1);

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Current(sem,start, i * 10, i * 10 + 10), "Th" + i);
        }
        for (Thread th : threads) {
            try {
                start.acquire();
                th.start();
            } catch (InterruptedException e) {

            }
        }
    }

}

class Current implements Runnable {

    Semaphore sem;
    
    Semaphore start;

    int min, max;

    public Current(Semaphore sem,Semaphore start, int min, int max) {
        this.sem = sem;
        this.start = start;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
            for (int i = min; i < max; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            sem.release();
        } catch (InterruptedException e) {

        }
        start.release();

    }

}

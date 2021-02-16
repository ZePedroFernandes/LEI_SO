
import java.util.concurrent.*;

public class Exerc1 implements Runnable {

    Semaphore sem;
    int i;

    public Exerc1(Semaphore sem, int i) {
        this.sem = sem;
        this.i = i;
    }

    private void writeToFile(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }

    public void run() {
        try {
            sem.acquire();	// início da secção crítica;

            writeToFile(i * 200, i * 200 + 199);

            sem.release();	// fim da secção crítica;
        } catch (InterruptedException iex) {
        }
    }

    public static void main(String args[]) {
        final Semaphore sem = new Semaphore(0, true);
        Thread[] ths = new Thread[10];

        for (int i = 0; i < 10; i++) {
            ths[i] = new Thread(new Exerc1(sem, i), "Th" + i);
        }

        for (int i = 0; i < 10; i++) {
            ths[i].start();
        }

        System.out.println("[Main] All threads created!");
        sem.release();	// 1 recurso
    }
}

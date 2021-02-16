import java.util.concurrent.*;
import java.util.Random;

public class Th2 implements Runnable {
    final private int MAX = 10;
    private Semaphore sem;

	public Th2 (Semaphore sem) { this.sem = sem; }

	public void run() {
		try{
    		sem.acquire();  // aguarda que Th1 termine

            Random gerador = new Random();
			String myname = Thread.currentThread().getName();

    		System.out.println("["+myname+"]: " + gerador.nextInt( MAX ));
		} catch (InterruptedException iex){}	
	}
}


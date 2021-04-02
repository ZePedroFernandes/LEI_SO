import java.util.concurrent.*;
import java.util.Scanner;

public class Current2 implements Runnable {
	Semaphore s;

	public Current2 (Semaphore sem) {s=sem;}	

    public void run() {
		try{
			String myname = Thread.currentThread().getName();
			System.out.println("["+myname+"] I'm here.");
			while(true){
				s.acquire();
				s.drainPermits();
				System.out.println("["+myname+"] "+(s.availablePermits()+1));
				Thread.sleep(1000);
				s.release();
				}
		} catch (InterruptedException iex){}	
	}

    public static void main(String args[]) {
		final Semaphore[] sems = new Semaphore[10];
		Thread[] ths = new Thread[10];
		Scanner keyboard = new Scanner(System.in);
		
		for (int i=0; i<ths.length; i++) {
			sems[i] = new Semaphore(0);
			ths[i] = new Thread(new Current2(sems[i]),"Th"+i);
		}		
		for (int i=0; i<ths.length; i++) {
			ths[i].start();
		}		

		System.out.println("[Main] All threads created!");
		boolean flag=true;
		//sems[0].release();
		while(flag) {
		System.out.println("[Main] Thread number to awake?:");
		int temp=Integer.parseInt(keyboard.nextLine());
		if(temp >= 0 && temp < 10) {
			sems[temp].release();
			System.out.println("[Main] Released thread number "+temp);
			sems[temp].drainPermits();
			System.out.println("[Main] Thread number "+temp+" with "+sems[temp].availablePermits() +" permits");
			while(sems[temp].availablePermits() >= 1) {
			try{
				sems[temp].acquire();
				System.out.println("[Main] Suspended thread number "+temp);
				} catch (InterruptedException iex){}
			}
		}
		if (temp>=10) {
			flag=false;
			System.out.println("[Main] quiting!");
			for(int i=0; i<ths.length; i++)
				ths[i].interrupt();
		}
	}
    }
}

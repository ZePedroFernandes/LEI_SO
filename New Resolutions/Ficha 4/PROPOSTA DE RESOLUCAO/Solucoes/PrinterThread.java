public class PrinterThread extends Thread {

	protected CounterSync counter = null;

	public PrinterThread( CounterSync counter ) {
		this.counter = counter;
	}

	public void run() {
		synchronized (counter) {
			for (int i = 0; i < 100; i++) {
				counter.add( 1 );
				System.out.println( Thread.currentThread().getName() + counter.getCount() );
			}
		}
	}

}


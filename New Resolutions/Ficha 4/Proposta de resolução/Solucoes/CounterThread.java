public class CounterThread extends Thread {

	protected int wait = 0;
	protected Counter counter = null;

	public CounterThread( Counter counter, int wait ) {
		this.counter = counter;
		this.wait = wait;
	}

	public void run() {
		try {
			Thread.sleep( this.wait );
		} catch ( InterruptedException e ) {}

		for (int i = 0; i < 10; i++) {
			counter.add( i );
		}
	}

}


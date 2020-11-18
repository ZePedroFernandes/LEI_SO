public class Exerc1 {

	public static void main (String [] args) {
		Counter counter = new Counter();
		CounterThread threadA = new CounterThread( counter );
		CounterThread threadB = new CounterThread( counter );

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch ( InterruptedException ie ) {}

		System.out.print( counter.get_count() );
	}

}


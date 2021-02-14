public class Exerc1 {

	public static void main (String [] args) {
		Counter counter = new Counter();
		CounterThread threadA = new CounterThread( counter, 0 );	// A vai aguardar 0 milisegundos;
		CounterThread threadB = new CounterThread( counter, 10 );	// B vai aguardar 10 milisegundos

		try {
			threadA.start();
			//Thread.sleep( 10 );	// perda de concorrência, já que A executa e B ainda não foi lançado
			threadB.start();

			threadA.join();
			threadB.join();
		} catch ( InterruptedException ie ) {}

		System.out.println( counter.getCount() );
	}

}


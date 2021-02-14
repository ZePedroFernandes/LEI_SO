public class Exerc4 {

	public static void main (String [] args) {
		CounterSync counter = new CounterSync();
		PrinterThread threadI[] = new PrinterThread[10];

		for (int c = 0; c < 10; c++ ) {
			threadI[c] = new PrinterThread( counter );
			threadI[c].setName( "[TH" + c + "]: " );
			threadI[c].start();
		}

		for (int c = 0; c < 10; c++ ) {
			try {
				threadI[c].join();
			} catch ( InterruptedException ie ) {}
		}

		System.out.println( "Impressão terminada" );
	}

}


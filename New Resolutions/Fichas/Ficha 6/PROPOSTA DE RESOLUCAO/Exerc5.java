public class Exerc5
{
	private static final int NUM_CONSUMERS = 2;	// 2 - número de consumidores

	public static void main(String [] args)
	{

		Buffer b = new Buffer( 5 );	// 5 - tamanho do buffer

		Producer p = new Producer( b );
		Consumer c[] = new Consumer[NUM_CONSUMERS];

		p.start();

		for ( int i = 0; i < NUM_CONSUMERS; i++ ) {	
			c[i] = new Consumer( b, i );
			c[i].start();
		}
	}
}

class Monitor
{
	;
}

class Buffer 
{
	private char [] buffer;
	private int count = 0, in = 0/*, out = 0*/;

	Buffer( int size )
	{
		buffer = new char[size];
	}

	public synchronized void put( String s ) 
	{
		for ( in = 0; in < s.length(); in++ ) {
			while ( count == buffer.length ) {
				try {
					this.notify();
					this.wait();
				} catch ( InterruptedException ie ) {}
			}

			if ( count == 0 ) {
				System.out.print( "\nProducing: " );
			}

			char c = s.charAt( in );

			System.out.print( c );
			buffer[count++] = c;
		}

		if ( count > 0 ) {
			this.notify();	// ativa apenas um dos consumidores
		}
	}

	public synchronized void get(int id) 
	{
		while ( true ) {
			while ( count < buffer.length ) {
				try {
					this.wait();
				} catch ( InterruptedException ie ) {}

				System.out.print("\nConsuming(" + id + "): ");
			}

			for ( int i = 0; i < count; i++ ) {
				char c = buffer[i];

				//++out;
				System.out.print( c );
			}

			count = 0;
			this.notifyAll();
		}
	}
}
     

class Producer extends Thread 
{
	private Buffer buffer;

	private String text = "Eu vi um sapo, a encher o papo. Tudo comeu, nada ofereceu...";

	Producer( Buffer b ) 
	{ 
		buffer = b;
	}

	public void run() 
	{
		try {
			Thread.sleep( 1000 );
		} catch ( InterruptedException ie ) {}

		buffer.put( text );
	}
}    

class Consumer extends Thread 
{
	private Buffer buffer;
	private int id;

	Consumer( Buffer b, int id ) 
	{ 
		buffer = b; 
		this.id = id;
	}

	public void run() 
	{
		buffer.get( id ); 
	}
}   



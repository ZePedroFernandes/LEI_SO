public class Exerc3Sub implements Runnable {

	private void printMessage() {
		try {
			for ( int i = 1; i <= 10; i++ ) {
				if ( Thread.interrupted() ) return;

				Thread.sleep( 1000 );
				System.out.println( Thread.currentThread().getName() + ": linha " + i );
			}
		} catch (InterruptedException e) {}
	}

	public void run() {
		System.out.println( Thread.currentThread().getName() + " Eu sou uma thread, dude...");
		this.printMessage();
	}
}


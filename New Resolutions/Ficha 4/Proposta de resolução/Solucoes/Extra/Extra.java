class Extra {
	public static void main(String[] args) {
		BankAccount account = new BankAccount( 0 );	// especificado saldo inicial

		Thread alice = new Thread( new JobAliceBruno( account, 1250 ), "alice" );
		Thread bruno = new Thread( new JobAliceBruno( account, 1250 ), "bruno" );

		//alice.setName( "alice" );
		//bruno.setName( "bruno" );

		alice.start();
		bruno.start();

		try {
			alice.join();
			bruno.join();
		}
		catch ( InterruptedException e ) { return ; }
	}
}

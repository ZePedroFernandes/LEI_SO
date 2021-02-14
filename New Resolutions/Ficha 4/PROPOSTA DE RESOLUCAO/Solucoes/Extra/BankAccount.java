class BankAccount {
	private int balance;	// saldo

	public BankAccount( int balance ) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void getMoney( int amount ) {
		balance = balance - amount;
	}

	public void putMoney( int amount ) {
		balance += amount;
	}
}

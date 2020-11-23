public class Counter {

	private long count = 0;

	public synchronized void add( long value ) {
		this.count += value;
	}

	public synchronized long get_count() {
		return count;
	}
}


public class SearcherThread extends Thread {
	protected RecordValue recValue;
	protected int[] array;
	protected int start, end;

	public SearcherThread(RecordValue recValue, int[] array, int start, int end) {
		this.recValue = recValue;
		this.array = array;
		this.start = start;
		this.end = end;
	}

	public void run() {
		int biggest = 0;

		for (int i = this.start; i < this.end; i++) {
			if ( this.array[i] > biggest ) biggest = this.array[i];
		}

		synchronized (recValue) { // métodos getMax() e setMaxPair() devem, os dois, executar por thread (sem troca de execução com outro thread)
			if (recValue.getMax() < biggest) recValue.setMaxPair(Thread.currentThread().getName(), biggest);
		}
		
		System.out.println("O thread " + Thread.currentThread().getName() + " terminou a pesquisa (maior valor: " + biggest + ")");
	}
}


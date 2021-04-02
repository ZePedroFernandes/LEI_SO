
import java.util.*;

public class Exerc5 {

    public static class SearcherThread extends Thread {

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
                if (this.array[i] > biggest) {
                    biggest = this.array[i];
                }
            }

            synchronized (recValue) { // métodos getMax() e setMaxPair() devem, os dois, executar por thread (sem troca de execução com outro thread)
                if (recValue.getMax() < biggest) {
                    recValue.setMaxPair(Thread.currentThread().getName(), biggest);
                }
            }

            System.out.println("O thread " + Thread.currentThread().getName() + " terminou a pesquisa (maior valor: " + biggest + ")");
        }
    }

    public static class RecordValue {

        private String name = null;
        private int max = 0;

        public void setMaxPair(String name, int max) {
            this.name = name;
            this.max = max;
        }

        public int getMax() {
            return this.max;
        }

        public String getName() {
            return this.name;
        }
    }

    static final int ARRAY_SIZE = 1000;
    static final int THREADS_COUNT = 5;

    private static int[] fillArray(int size) {
        int array[] = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1000);	// gera números entre 0 e 999
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = fillArray(ARRAY_SIZE);

        RecordValue recValue = new RecordValue();
        SearcherThread sthread[] = new SearcherThread[THREADS_COUNT];

        for (int c = 0; c < THREADS_COUNT; c++) {			// exemplos
            int start = c * (array.length / THREADS_COUNT);		// para o 1º thread: start = 0 * (1000/5) = 0; end = 0 + (1000/5) = 200
            int end = start + (array.length / THREADS_COUNT);   	// para o 2º thread: start = 1 * (1000/5) = 200; end = 200 + (1000/5) = 400

            sthread[c] = new SearcherThread(recValue, array, start, end);
            sthread[c].setName("[TH" + c + "]");
            sthread[c].start();
        }

        for (int c = 0; c < THREADS_COUNT; c++) {
            try {
                sthread[c].join();
            } catch (InterruptedException ie) {
            }
        }

        System.out.println("O thread " + recValue.getName() + " encontrou o maior valor: " + recValue.getMax());
    }
}

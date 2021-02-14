
import java.util.Random;


public class Exerc5 {

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        SharedObj shared = new SharedObj();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new SearchRunnable(array, i * 200, i * 200 + 200, shared));
            threads[i].start();
        }

        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException ex) {
            }
        }

        System.out.println(shared.getValue());
    }

    public static class SearchRunnable implements Runnable {

        int[] array;
        int startIndex;
        int endIndex;
        SharedObj shared;

        public SearchRunnable(int[] array, int start, int end, SharedObj shared) {
            this.array = array;
            this.startIndex = start;
            this.endIndex = end;
            this.shared = shared;
        }

        @Override
        public void run() {
            int biggest = array[startIndex];
            for (int i = startIndex + 1; i < endIndex; i++) {
                if (array[i] > biggest) {
                    biggest = array[i];
                }
            }
            synchronized (shared) {
                if (biggest > shared.getValue()) {
                    shared.setValue(biggest);
                }
            }
        }

    }

    public static class SharedObj {

        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}


public class Exerc4 {

    public static class CounterSync {

        long count = 0;

        public synchronized void add(long value) {
            this.count += value;
        }

        public long getCount() {
            return count;
        }
    }

    public static class PrinterThread extends Thread {

        protected CounterSync counter = null;

        public PrinterThread(CounterSync counter) {
            this.counter = counter;
        }

        public void run() {
            synchronized (counter) {
                for (int i = 0; i < 100; i++) {
                    counter.add(1);
                    System.out.println(Thread.currentThread().getName() + counter.getCount());
                }
            }
        }

    }

    public static void main(String[] args) {
        CounterSync counter = new CounterSync();
        PrinterThread threadI[] = new PrinterThread[10];

        for (int c = 0; c < 10; c++) {
            threadI[c] = new PrinterThread(counter);
            threadI[c].setName("[TH" + c + "]: ");
            threadI[c].start();
        }

        for (int c = 0; c < 10; c++) {
            try {
                threadI[c].join();
            } catch (InterruptedException ie) {
            }
        }

        System.out.println("Impressão terminada");
    }

}

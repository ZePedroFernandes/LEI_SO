
public class Exerc4 {

    public static void main(String[] args) {
        SharedObj obj = new SharedObj();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PrinterThread(obj), "Thread" + i);
            threads[i].start();
        }

        for (Thread printerThread : threads) {
            try {
                printerThread.join();
            } catch (InterruptedException ex) {
            }
        }

        System.out.println("Impressao terminada");
    }

    public static class PrinterThread implements Runnable {

        private SharedObj sharedObj;

        public PrinterThread(SharedObj sharedObj) {
            this.sharedObj = sharedObj;
        }

        @Override
        public void run() {
            synchronized (sharedObj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + sharedObj.getCount());
                    this.sharedObj.incrementCount();
                }
            }
        }

    }

    public static class SharedObj {

        private int count = 0;

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            this.count++;
        }
    }
}

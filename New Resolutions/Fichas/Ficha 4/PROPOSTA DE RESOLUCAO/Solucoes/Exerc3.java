
public class Exerc3 {

    public static class Counter {

        private long count = 0;

        public void add(long value) {
            this.count += value;
        }

        public long getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        long start = 0, end = 0;

        Counter counter = new Counter();	// para usar depois com a solução sleep
        CounterSync counterSync = new CounterSync();	// para usar depois com a solução synchronized

        CounterThread threadA = new CounterThread(counter, 0);
        CounterThread threadB = new CounterThread(counter, 10);

        CounterThreadSync threadAA = new CounterThreadSync(counterSync);
        CounterThreadSync threadBB = new CounterThreadSync(counterSync);

        start = System.nanoTime();

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException ie) {
        }

        end = System.nanoTime();

        System.out.println(counter.getCount() + " - solução sleep: " + (end - start) / 1000 + " us");	// microsegundos (10^-6 segundos)

        // --------------------------------------------------------------------
        start = System.nanoTime();

        threadAA.start();
        threadBB.start();

        try {
            threadAA.join();
            threadBB.join();
        } catch (InterruptedException ie) {
        }

        end = System.nanoTime();

        System.out.println(counterSync.getCount() + " - solução synchronized: " + (end - start) / 1000 + " us");
    }

}

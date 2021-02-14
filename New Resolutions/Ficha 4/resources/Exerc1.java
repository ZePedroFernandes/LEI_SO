
public class Exerc1 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterThread threadA = new CounterThread(counter);
        CounterThread threadB = new CounterThread(counter);

        threadA.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        System.out.println(counter.get_count());
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException ie) {
        }

        System.out.println(counter.get_count());
    }

    public static class Counter {

        private long count = 0;

        public void add(long value) {
            this.count += value;
        }

        public long get_count() {
            return count;
        }
    }

    public static class CounterThread extends Thread {

        protected Counter counter = null;

        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                counter.add(i);
            }
        }

    }

}

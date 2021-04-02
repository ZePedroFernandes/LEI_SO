
public class Exerc2 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterThread threadA = new CounterThread(counter);
        CounterThread threadB = new CounterThread(counter);

        threadA.start();
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

        public synchronized void add(long value) {
            this.count += value;
        }

        public long get_count() {
            return count;
        }
    }

    public static class CounterThread extends Thread {

        protected Counter counter;

        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            System.out.println(counter.get_count());
            for (int i = 0; i < 10; i++) {
                counter.add(i);
            }
        }

    }

}

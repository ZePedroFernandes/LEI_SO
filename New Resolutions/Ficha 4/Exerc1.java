
public class Exerc1 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterThread threadA = new CounterThread(counter,0);
        CounterThread threadB = new CounterThread(counter,50);

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

        public void add(long value) {
            this.count += value;
        }

        public long get_count() {
            return count;
        }
    }

    public static class CounterThread extends Thread {

        protected Counter counter = null;
        protected int wait;

        public CounterThread(Counter counter, int sleepTime) {
            this.counter = counter;
            this.wait = sleepTime;
        }

        @Override
        public void run() {
            
            try {
                Thread.sleep(wait);
            } catch (InterruptedException ex) {
            }
            for (int i = 0; i < 10; i++) {
                counter.add(i);
            }
        }

    }

}

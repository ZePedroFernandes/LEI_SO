
public class CounterThreadSync extends Thread {

    protected CounterSync counter = null;

    public CounterThreadSync(CounterSync counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.add(i);
        }
    }

}

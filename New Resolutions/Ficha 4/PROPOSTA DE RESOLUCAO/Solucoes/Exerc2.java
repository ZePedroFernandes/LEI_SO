
public class Exerc2 {

    public static void main(String[] args) {
        CounterSync counter = new CounterSync();
        CounterThreadSync threadA = new CounterThreadSync(counter);
        CounterThreadSync threadB = new CounterThreadSync(counter);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException ie) {
        }

        System.out.println(counter.getCount());
    }

}

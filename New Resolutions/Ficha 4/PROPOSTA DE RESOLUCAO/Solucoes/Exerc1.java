
public class Exerc1 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterThread threadA = new CounterThread(counter, 0);	// A vai aguardar 0 milisegundos;
        CounterThread threadB = new CounterThread(counter, 10);	// B vai aguardar 10 milisegundos

        try {
            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();
        } catch (InterruptedException ie) {
        }

        System.out.println(counter.getCount());
    }

}

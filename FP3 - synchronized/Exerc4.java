
public class Exerc4 {

    public static void main(String[] args) {
        SharedNumber sharedNumber = new SharedNumber();
        printHundredNumbersThread[] threads = new printHundredNumbersThread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new printHundredNumbersThread(sharedNumber);
            threads[i].setName("Thread[" + i + "]");
            threads[i].start();
        }

    }
}


public class Exerc5 {

    public static void main(String[] args) {
        int[] array = arrayOneTo1000();

        SharedNumber sharedNumber = new SharedNumber();
        searchArrayHigher[] threads = createThreads(5, sharedNumber, array);

        for (searchArrayHigher thread : threads) {
            thread.start();
        }

        try {
            for (searchArrayHigher thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sharedNumber.getNumber());
    }

    private static int[] arrayOneTo1000() {
        int[] numbers = new int[1000];
        for (int i = 1; i < 1001; i++) {
            numbers[i - 1] = (i + 500) % 1000;
        }
        return numbers;
    }

    private static searchArrayHigher[] createThreads(int threadNumber, SharedNumber sharedNumber, int[] array) {
        searchArrayHigher[] threads = new searchArrayHigher[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new searchArrayHigher(array, sharedNumber);
            threads[i].initialPosition = (array.length / 5) * i;
            threads[i].finalPosition = threads[i].initialPosition + (array.length / 5) - 1;
            threads[i].setName("Thread[" + i + "]");
        }

        return threads;
    }

}

class searchArrayHigher extends Thread {

    int[] numbers;

    SharedNumber sharedHigherNumber;

    int initialPosition, finalPosition;

    public searchArrayHigher(int[] numbers, SharedNumber sharedHigherNumber) {
        this.sharedHigherNumber = sharedHigherNumber;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int result = Integer.MIN_VALUE;
        for (int i = initialPosition; i < finalPosition; i++) {
            if (numbers[i] > sharedHigherNumber.getNumber()) {
                result = numbers[i];
            }
        }
        sharedHigherNumber.setHigher(result);
    }
}

class SharedNumber {

    private int number = Integer.MIN_VALUE;

    public int getNumber() {
        return this.number;
    }

    public synchronized void setHigher(int number) {
        if (number > this.number) {
            this.number = number;
        }
    }
}

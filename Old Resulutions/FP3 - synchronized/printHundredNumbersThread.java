public class printHundredNumbersThread extends Thread {

    SharedNumber sharedNumber;

    public printHundredNumbersThread(SharedNumber Sharednumber) {
        this.sharedNumber = Sharednumber;
    }

    @Override
    public void run() {
        synchronized (sharedNumber) {
            for (int i = 0; i < 100; i++) {
                System.out.println("" + this.getName() + ":" + sharedNumber.getNumber());
                sharedNumber.addNumber(1);
            }
        }
    }
}

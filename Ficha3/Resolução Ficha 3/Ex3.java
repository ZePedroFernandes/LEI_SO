
public class Ex3 implements Runnable {

    @Override
    public void run() {

        try {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("Linha " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }

    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new Ex3());
        myThread.start();

        int secs = Integer.parseInt(args[0]) * 1000;
        try {
            Thread.sleep(secs);
            myThread.interrupt();
        } catch (InterruptedException e) {
        }

    }

}

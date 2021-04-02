
public class Exerc2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Deve passar um argumento, Ex: java Exerc2 2");
            System.exit(1);
        }

        int interruptTime = 0;

        try {
            interruptTime = Integer.parseInt(args[0]);

            if (interruptTime < 0) {
                System.out.println("Deve passar um número positivo ou zero");
                System.exit(1);
            } else if (interruptTime > 10) {
                System.out.println("argument max value is 10");
                System.exit(1);
            }

        } catch (NumberFormatException exception) {
            System.out.println("Deve passar como argumento um número inteiro!");
            System.exit(1);
        }

        Thread printThread = new Thread(new myRunnable());
        printThread.start();
        try {
            Thread.currentThread().sleep(interruptTime * 1000);
            printThread.interrupt();
            printThread.join();
        } catch (InterruptedException e) {
        }
    }

    private static class myRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("[Print " + i + "] Linha " + i);
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

    }
}

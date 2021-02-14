
public class Exerc1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Deve passar um argumento, Ex: java Exerc1 2");
            System.exit(1);
        }

        int threadNumber = 0;

        try {
            threadNumber = Integer.parseInt(args[0]);

            if (threadNumber < 1) {
                System.out.println("Deve passar um número positivo");
                System.exit(1);
            } else if (threadNumber > 10000) {
                System.out.println("Thread max number is 1000");
                System.exit(1);
            }

        } catch (NumberFormatException exception) {
            System.out.println("Deve passar como argumento um número inteiro!");
            System.exit(1);
        }

        for (int i = 0; i < threadNumber; i++) {
            Thread th = new Thread(new myThread(), Integer.toString(i));
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
            }
        }
    }

    private static class myThread implements Runnable {

        @Override
        public void run() {
            System.out.println("[Th" + Thread.currentThread().getName() + "] Eu sou uma thread");
        }

    }
}


public class Exerc3Main {

    public static class Exerc3Sub implements Runnable {

        private void printMessage() {
            try {
                for (int i = 1; i <= 10; i++) {
                    if (Thread.interrupted()) {
                        return;
                    }

                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": linha " + i);
                }
            } catch (InterruptedException e) {
            }
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Eu sou uma thread, dude...");
            this.printMessage();
        }
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Modo de uso: java Exerc3Main <número de segundos em espera>");
            System.exit(0);
        }

        Thread print = new Thread(new Exerc3Sub(), "[Th0]");

        try {
            print.start();
            Thread.sleep(Integer.parseInt(args[0]) * 1000);
            print.interrupt();

            // wait for threads to end
            print.join();
        } catch (InterruptedException | IllegalThreadStateException | SecurityException | NumberFormatException e) {
            System.out.println("teste 2");
        }
    }

}

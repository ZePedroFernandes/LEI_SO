
public class Ex1 implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName() + "Eu sou uma thread ");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Modo de uso: java Exerc1 <número de threads a criar>");
            return;
        }
        try {
            final int threadNumber = Integer.parseInt(args[0]);
            for (int i = 0; i < threadNumber; i++) {
                Ex1 ex1 = new Ex1();
                Thread thread = new Thread(ex1);
                thread.setName("[Th" + i + "]");
                thread.start();
                //Imprimir por ordem.
                //thread.join();
            }

        } catch (NumberFormatException e) {
            System.out.println("Insert a number");
        } catch (Exception e) {
            System.out.println("Something went bad");
        }

    }
}

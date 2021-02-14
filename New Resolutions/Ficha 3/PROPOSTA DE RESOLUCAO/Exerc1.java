
public class Exerc1 implements Runnable {

    public void run() {
        System.out.println("Eu sou uma thread, dude...");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Modo de uso: java Exerc1 <número de threads a criar>");
            System.exit(0);
        }

        final int count = Integer.parseInt(args[0]);

        Thread[] th = new Thread[count];

        for (int i = 0; i < count; i++) {
            Exerc1 ex1 = new Exerc1();
            th[i] = new Thread(ex1);
            th[i].start();

            // numa linha só
            // ( new Thread( new Exerc1() ) ).start();
        }
    }

}

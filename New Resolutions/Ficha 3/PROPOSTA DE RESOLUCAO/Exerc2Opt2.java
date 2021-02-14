
public class Exerc2Opt2 implements Runnable {

    public void run() {
        System.out.println("[Th" + Thread.currentThread().getName() + "] Eu sou uma thread, dude...");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Modo de uso: java Exerc2Opt2 <número de threads a criar>");
            System.exit(0);
        }

        final int count = Integer.parseInt(args[0]);

        Thread[] th = new Thread[count];

        for (int i = 0; i < count; i++) {
            Exerc2Opt2 ex2 = new Exerc2Opt2();
            th[i] = new Thread(ex2, Integer.toString(i));
            th[i].start();

            // numa linha só
            // ( new Thread( new Exerc1() ) ).start();
        }
    }

}

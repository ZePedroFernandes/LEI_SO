
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Exerc1 {

    public static class Printer implements Runnable {

        Semaphore sem;

        int i;

        File file;

        public Printer(Semaphore sem, int i, File file) {
            this.sem = sem;
            this.i = i;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                String myName = Thread.currentThread().getName();
                for (int start = i; start < i + 20; start++) {
                    bw.write("[" + myName + "]" + ": line " + start + "\n");
                }
                bw.close();
                sem.release();
            } catch (IOException e) {

            }
        }

    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Modo de uso: java Exerc1 <nome do ficheiro>");
            System.exit(1);
        }
        File file = new File(args[0]);

        Semaphore sem = new Semaphore(1);

        for (int i = 0; i < 5; i++) {
            Thread th = new Thread(new Printer(sem, i * 20, file), "Th" + i);
            try {
                sem.acquire();
            } catch (InterruptedException ex) {
            }
            th.start();
        }

    }
}

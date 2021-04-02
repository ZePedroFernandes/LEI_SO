
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.concurrent.*;

public class Exerc1 implements Runnable {

    protected Monitor m;
    protected int i;

    public Exerc1(Monitor m, int i) {
        this.m = m;
        this.i = i;
    }

    public void run() {
        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel l = new JLabel("#");

        f.add(l);
        f.setSize(150, 200);
        f.setLocation(i * 200, 100);
        f.setVisible(true);

        m.espera();	// main thread deve executar "accordaTodas()" depois de todos os threads executarem "espera()"

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }

            l.setText(" " + l.getText() + "#");
        }

        f.dispose();
    }

    public static void main(String args[]) {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[5];	// 8

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Exerc1(mon, i), "Th" + i);
            ths[i].start();
        }

        System.out.println("[Main] All threads created!");
        System.out.println("[Main] Activating threads!");

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException ie) {
        }

        mon.acordaTodas();

        try {
            for (int i = 0; i < ths.length; i++) {
                ths[i].join();
            }

        } catch (InterruptedException ie) {
        }
        System.out.println("[Main] All threads ended!");

    }

    public static class Monitor {

        protected int vez = 0;

        public synchronized void acordaTodas() {
            this.notifyAll();
        }

        public synchronized void acorda() {
            this.notify();
        }

        public synchronized void espera() {
            try {
                this.wait();
            } catch (InterruptedException ie) {
            }
        }

        public synchronized void defineVez(int vez) {
            this.vez = vez;
        }

        public synchronized int leVez() {
            return this.vez;
        }
    }

}

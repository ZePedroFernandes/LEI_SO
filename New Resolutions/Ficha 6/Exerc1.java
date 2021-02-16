
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exerc1 implements Runnable {

    protected Monitor m;
    protected int pos;

    public Exerc1(Monitor m, int i) {
        this.m = m;
        this.pos = i;
    }

    public void run() {
        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel label = new JLabel("#");
        f.add(label);
        f.setSize(170, 200);
        if (pos > 3) {
            f.setLocation((pos - 4) * 200, 400);
        } else {
            f.setLocation(pos * 200, 100);
        }

        f.setVisible(true);
        m.espera();

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
            label.setText("" + label.getText() + "#");
        }
        f.dispose();
    }

    public static void main(String args[]) {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[8];

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Exerc1(mon, i), "Th" + i);
            ths[i].start();
        }
        System.out.println("[Main] All threads created!");
        System.out.println("[Main] Activting threads!");


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        mon.acordaTodas();

        try {
            for (Thread th : ths) {
                th.join();
            }
        } catch (InterruptedException ie) {
        }
        System.out.println("[Main] All threads ended!");
    }

    public static class Monitor {

        public synchronized void acordaTodas() {
            this.notifyAll();
        }

        public synchronized void espera() {
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
    }

}

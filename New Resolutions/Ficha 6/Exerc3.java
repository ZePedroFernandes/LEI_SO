
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exerc3 implements Runnable {

    protected Monitor m;
    protected int pos;

    public Exerc3(Monitor m, int i) {
        this.m = m;
        this.pos = i;
    }

    public void run() {
        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel label = new JLabel("#");
        f.add(label);
        f.setSize(300, 200);
        if (pos > 3) {
            f.setLocation((pos - 4) * 300, 400);
        } else {
            f.setLocation(pos * 300, 100);
        }

        f.setVisible(true);
        do {
            m.espera();
        } while (m.getNextThread() != pos);

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
            ths[i] = new Thread(new Exerc3(mon, i), "Th" + i);
            ths[i].start();
        }
        System.out.println("[Main] All threads created!");
        System.out.println("[Main] Activting threads!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        for (int i = 0; i < ths.length; i++) {
            mon.setNextThread(i);
            mon.acordaTodas();

            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ie) {
            }
        }

        try {
            for (Thread th : ths) {
                th.join();
            }
        } catch (InterruptedException ie) {
        }
        System.out.println("[Main] All threads ended!");
    }

    public static class Monitor {

        int nextThread;

        public synchronized void acordaTodas() {
            this.notifyAll();
        }

        public synchronized void espera() {
            try {
                this.wait();
            } catch (InterruptedException ie) {
            }
        }

        public synchronized void acorda() {
            this.notify();
        }

        public void setNextThread(int i) {
            this.nextThread = i;
        }

        public int getNextThread() {
            return this.nextThread;
        }
    }

}

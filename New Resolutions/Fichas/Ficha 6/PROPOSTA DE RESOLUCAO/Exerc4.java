
public class Exerc4 {

    public static void main(String[] args) {
        Buffer b = new Buffer();
        Consumer c = new Consumer(b);

        c.start();

        for (int i = 0; i < 10; i++) {
            synchronized (b) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                }

                b.put((char) ('A' + i % 26));
                b.setFlag(true);	// há conteúdo para ler...
                b.notify();

                while (b.getFlag()) {
                    try {
                        b.wait();	// espera pelo consumo...
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        try {
            c.join();
        } catch (InterruptedException ie) {
        }

        System.out.println("All is done: zoolander 2 ;)");
    }
}

class Buffer {

    private char buffer;
    private boolean flag = false;
    private int count = 0, in = 0, out = 0;

    public synchronized void setFlag(boolean f) {
        flag = f;
    }

    public synchronized boolean getFlag() {
        return flag;
    }

    public synchronized void put(char c) {
        System.out.println("Putting " + c + " ...");
        buffer = c;
    }

    public synchronized char get() {
        char c = buffer;
        System.out.println("Consuming " + c + " ...");

        return c;
    }
}

class Consumer extends Thread {

    private Buffer buffer;

    Consumer(Buffer b) {
        buffer = b;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (buffer) {
                while (!buffer.getFlag()) {	// espera pela produção
                    try {
                        buffer.wait();
                    } catch (InterruptedException ie) {
                    }
                }

                buffer.get();
                buffer.setFlag(false);	// não há conteúdo para ler...
                buffer.notify();
            }
        }
    }
}

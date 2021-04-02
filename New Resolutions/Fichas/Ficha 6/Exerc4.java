
public class Exerc4 {

    public static class Buffer {

        private char letter;

        final int MAX = 10;

        int count = 0;

        public Buffer() {
        }

        public char getLetter() {
            return letter;
        }

        public void setLetter(char letter) {
            if (count < MAX) {
                this.letter = letter;
                count++;
            }
        }

        public synchronized void acordar() {
            this.notify();
        }

        public synchronized void esperar() {
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }

        public boolean isMaxed() {
            return this.count >= this.MAX;
        }
    }

    public static class Reader implements Runnable {

        Buffer buffer;

        public Reader(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                buffer.esperar();
                System.out.println("Consuming " + buffer.getLetter() + " ...");
                buffer.acordar();
                if (buffer.isMaxed()) {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread readerThread = new Thread(new Reader(buffer));
        readerThread.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            char letter = (char) (65 + i);
            System.out.println("Putting " + letter + " ...");
            buffer.setLetter(letter);
            buffer.acordar();

            if (!buffer.isMaxed()) {
                buffer.esperar();
            }
        }

        try {
            readerThread.join();
        } catch (InterruptedException e) {
        }
        System.out.println("All is done : zoolander 2 ;)");

    }
}

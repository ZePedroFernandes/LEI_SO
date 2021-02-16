
public class ExercExtra {

    public static class Buffer {

        private char[] letters = new char[5];

        int count = 0;

        public Buffer() {
        }

        public char[] getLetters() {
            char[] res = this.letters;
            this.letters = new char[5];
            this.count = 0;
            return res;
        }

        public void addLetter(char letter) {
            if (count < 5) {
                this.letters[count] = letter;
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

        public int size() {
            return this.count;
        }

//        public boolean isMaxed() {
//            return this.count >= this.MAX;
//        }
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
                if (buffer.size() == 0) {
                    break;
                }
                int size = buffer.size();
                char[] letters = buffer.getLetters();
                for (int i = 0; i < size; i++) {
                    System.out.println("Consuming " + letters[i] + " ...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
                buffer.acordar();
            }
        }

    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread readerThread = new Thread(new Reader(buffer));
        readerThread.start();

        for (int i = 0; i < 26; i++) {
            char letter = (char) (65 + i);
            System.out.println("Putting " + letter + " ...");
            buffer.addLetter(letter);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }

            if (buffer.size() == 5 || i == 25) {
                buffer.acordar();
                buffer.esperar();
            }
        }
        buffer.acordar();

        try {
            readerThread.join();
        } catch (InterruptedException e) {
        }
        System.out.println("All is done : zoolander 2 ;)");

    }
}

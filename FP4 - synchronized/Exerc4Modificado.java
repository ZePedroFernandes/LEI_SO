
public class Exerc4Modificado {

    /**
     * Imprime os numeros de 0 a args[1] com um numero especificado de threads (args[0]).
     *
     * @param args argumentos de entrada.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Introduza dois argumentos!\nUm para o numero de "
                    + "threads e outro para a quantidade de numeros a imprimir");
            return;
        }
        
        int threadAmount;
        int numbersToPrint;
        
        try {
            threadAmount = Integer.parseInt(args[0]);
            numbersToPrint = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {
            System.out.println("Introduz o número de threads");
            return;
        }

        SharedNumber sharedNumber = new SharedNumber();
        printNumbers[] threads = new printNumbers[threadAmount];

        int resto = numbersToPrint % threadAmount;

        for (int i = 0; i < threadAmount - 1; i++) {
            threads[i] = new printNumbers(sharedNumber, numbersToPrint / threadAmount);
            threads[i].setName("Thread[" + i + "]");
            if (i != 0) {
                try {
                    threads[i - 1].join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            threads[i].start();
        }
        threads[threadAmount - 1] = new printNumbers(sharedNumber, numbersToPrint / threadAmount + resto);
        threads[threadAmount - 1].setName("Thread[" + (threadAmount - 1) + "]");
        threads[threadAmount - 1].start();

    }
}

class SharedNumber {

    int number;

    public SharedNumber() {
        this.number = 0;
    }

    public void addNumber(int number) {
        this.number += number;
    }

    public int getNumber() {
        return this.number;
    }
}

class printNumbers extends Thread {

    SharedNumber sharedNumber;
    int numbersToPrint;

    public printNumbers(SharedNumber Sharednumber, int numbersToPrint) {
        this.sharedNumber = Sharednumber;
        this.numbersToPrint = numbersToPrint;
    }

    @Override
    public void run() {
        synchronized (sharedNumber) {
            for (int i = 0; i < this.numbersToPrint; i++) {
                System.out.println("" + this.getName() + ":" + sharedNumber.getNumber());
                sharedNumber.addNumber(1);
            }
        }
    }
}

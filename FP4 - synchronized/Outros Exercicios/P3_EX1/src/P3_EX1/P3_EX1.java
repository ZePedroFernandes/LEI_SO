package P3_EX1;

import java.util.Random;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class P3_EX1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankAccount conta = new BankAccount();
        Random randomGen = new Random();

        PessoaThread[] pessoas = new PessoaThread[2];

        for (int i = 0; i < 6; i++) {
            pessoas[0] = new PessoaThread(conta, 1250, randomGen);
            pessoas[1] = new PessoaThread(conta, 1250, randomGen);
            pessoas[0].setName("Bruno");
            pessoas[1].setName("Alice");
            for (Thread thread : pessoas) {
                thread.start();
            }

            try {
                for (Thread thread : pessoas) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}

class PessoaThread extends Thread {

    BankAccount conta;

    int monthlyIncome;

    Random levantamento;

    public PessoaThread(BankAccount conta, int monthlyIncome, Random randomGenerator) {
        this.monthlyIncome = monthlyIncome;
        this.conta = conta;
        this.levantamento = randomGenerator;
    }

    @Override
    public void run() {
        conta.putMoney(1250);

        synchronized (conta) {
            // try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Levantamento:" + conta.getMoney(levantamento.nextInt(200) + 150));
                System.out.println("Saldo: " + conta.checkMoney());
            }
            /*} catch (BankAccount.saldoException ex) {
                System.out.println(ex.getMessage());
            }*/
        }
    }
}

class BankAccount {

    private int money = 0;

    public int checkMoney() {
        return money;
    }

    public void putMoney(int deposito) {
        money += deposito;
    }

    public int getMoney(int levantamento) {
        if (levantamento <= money) {
            money = money - levantamento;
            return levantamento;
        } else {
            return 0;
        }
    }

    public class saldoException extends Exception {

        public saldoException() {
        }

        public saldoException(String message) {
            super(message);
        }

    }
}

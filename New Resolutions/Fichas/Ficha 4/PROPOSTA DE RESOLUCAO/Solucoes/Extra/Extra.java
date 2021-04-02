
import java.util.Random;

public class Extra {

    public static class BankAccount {

        private int balance;	// saldo

        public BankAccount(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void getMoney(int amount) {
            balance = balance - amount;
        }

        public void putMoney(int amount) {
            balance += amount;
        }
    }

    public static class JobAliceBruno implements Runnable {

        private final BankAccount account;
        private int salary;

        public JobAliceBruno(BankAccount account, int salary) {
            this.account = account;
            this.salary = salary;
        }

        public void run() {
            for (int m = 1; m <= 6; m++) {	// 6 meses
                putSalaryInAccount(salary, m);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 10; i++) {	// 10 levantamentos
                    int amount = randomizeAmount(150, 350);

                    makeCashWithdrawal(amount);	// fazer levantamento
                }
            }
        }

        private void putSalaryInAccount(int salary, int month) {
            String name = Thread.currentThread().getName();

            synchronized (account) {
                System.out.println(name + ": " + "just got my €" + salary + " salary for month " + month + "!");
                account.putMoney(salary);	// método não precisa ser sinchronized... no então, um lock é reentrante ;)
                System.out.println(name + ": " + "account balance now is €" + account.getBalance());
            }
        }

        private void makeCashWithdrawal(int amount) {
            String name = Thread.currentThread().getName();

            synchronized (account) {	// em vez disto, uma alternativa passa por usar métodos com a keyword synchronized
                System.out.println(name + ": " + "trying to get €" + amount);
                int balance = account.getBalance();

                if (balance >= amount) {
                    System.out.println(name + ": " + "cool, there's €" + balance + " avaliable!");

                    try {
                        Thread.sleep(500);	// apenas para fazer algum tempo...
                    } catch (InterruptedException e) {
                    }

                    account.getMoney(amount);
                    System.out.println(name + ": €" + amount + " is with me :)");
                } else {
                    System.out.println(name + ": " + "there's not enough money, only €" + balance + "...");
                }
            }
        }

        private int randomizeAmount(int min, int max) {
            Random r = new Random();

            return r.nextInt(max - min) + min;
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(0);	// especificado saldo inicial

        Thread alice = new Thread(new JobAliceBruno(account, 1250), "alice");
        Thread bruno = new Thread(new JobAliceBruno(account, 1250), "bruno");

        alice.start();
        bruno.start();

        try {
            alice.join();
            bruno.join();
        } catch (InterruptedException e) {
        }
    }

}

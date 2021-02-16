
import java.util.Random;

public class ExercFinal {

    public static class BankAccount {

        private int money;

        public BankAccount() {
            this.money = 0;
        }

        public void putMoney(int money) {
            this.money += money;
        }

        public void getMoney(int money) {
            this.money -= money;
        }

    }

    public static class JobAliceBruno extends Thread {

        String name;

        int monthlySalary;

        BankAccount bankAccount;

        public JobAliceBruno(String name, int monthlySalary, BankAccount bankAccount) {
            this.name = name;
            this.monthlySalary = monthlySalary;
            this.bankAccount = bankAccount;
        }

        @Override
        public void run() {
            bankAccount.putMoney(monthlySalary);
            for (int i = 0; i < 10; i++) {
                int amount = new Random().nextInt(150) + 150;

                synchronized (this.bankAccount) {
                    if (bankAccount.money >= amount) {
                        bankAccount.getMoney(amount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();

        for (int i = 0; i < 6; i++) {
            JobAliceBruno Bruno = new JobAliceBruno("Bruno", 1250, bankAccount);
            JobAliceBruno Alice = new JobAliceBruno("Alice", 1250, bankAccount);
            Bruno.start();
            Alice.start();
            try {
                Bruno.join();
                Alice.join();
            } catch (InterruptedException e) {
            }
        }
    }
}

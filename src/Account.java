
class Account {
    public double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + " | New Balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdraw: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}

class Transaction implements Runnable {
    private Account account;
    private String type;
    private double amount;

    public Transaction(Account account, String type, double amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        if ("deposit".equals(type)) {
            account.deposit(amount);
        } else if ("withdraw".equals(type)) {
            account.withdraw(amount);
        }
    }
}
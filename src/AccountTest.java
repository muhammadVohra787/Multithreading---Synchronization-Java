import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(1000.0);
        System.out.println("Initial Balance is: " + account.balance);
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {

            ArrayList<Transaction> transactions = new ArrayList<>();
            transactions.add(new Transaction(account, "deposit", 200.0));
            transactions.add(new Transaction(account, "withdraw", 200.0));
            transactions.add(new Transaction(account, "deposit", 300.0));
            transactions.add(new Transaction(account, "withdraw", 300.0));

            for (Transaction transaction : transactions) {
                executor.execute(transaction);
            }

            executor.shutdown();
        }finally {
            System.out.println("Final Balance is: " + account.balance);
        }

    }
}

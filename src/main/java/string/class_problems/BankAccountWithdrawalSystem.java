
import java.util.*;

public class BankAccountWithdrawalSystem {
    static class Account {
        String type;
        int balance;

        Account(String type, int balance) {
            this.type = type;
            this.balance = balance;
        }

        String withdraw(int amount) {
            int newBalance = balance - amount;

            if (type.equals("Savings") && newBalance < 1000)
                return "rejected: minimum balance 1000";

            if (type.equals("Current") && newBalance < -5000)
                return "rejected: overdraft limit 5000";

            balance = newBalance;
            return "balance " + balance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("Savings") || p[0].equals("Current")) {
                accounts.put(p[1], new Account(p[0],
                        Integer.parseInt(p[2])));
            } else if (p[0].equals("WITHDRAW")) {
                Account a = accounts.get(p[1]);

                if (a == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println(p[1] + " " +
                            a.withdraw(Integer.parseInt(p[2])));
                }
            }
        }
        sc.close();
    }
}

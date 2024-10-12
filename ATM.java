import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Transaction history class to manage recording and displaying of transactions.
 */
class TransactionHistory {
    
    void add(int amt, int choice, List<String> transHist1) {
        if (choice == 2) {
            String transaction = "Rs. " + amt + " withdrawn";
            transHist1.add(transaction);
        } else {
            String transaction = "Rs. " + amt + " deposited";
            transHist1.add(transaction);
        }
    }

    void add(int amt, String recipient, List<String> transHist1) {
        String transaction = "Rs. " + amt + " transferred to " + recipient;
        transHist1.add(transaction);
    }

    /**
     * Displays the list of all transactions made.
     *
     * @param transHist1 The list of transactions.
     */
    void display(List<String> transHist1) {
        if (transHist1.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            for (int i = 0; i < transHist1.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": " + transHist1.get(i));
            }
        }
    }
}

/**
 * Handles user registration for the ATM system.
 */
class BankAccount {
    /**
     * Registers a new user with name, username, password, and account number.
     */
    static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your name :");
        ATM.name = sc.nextLine();
        System.out.println("Enter username :");
        String user = sc.nextLine();
        System.out.println("Enter password :");
        String pass = sc.nextLine();
        System.out.println("Enter your Account number :");
        ATM.accnumber = sc.nextLine();
        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("---------------------------");
        ATM.prompt();
    }
}

/**
 * Handles withdraw operations.
 */
class Withdraw {
    int withdraw(int balance, int amount) {
        return balance - amount;
    }
}

/**
 * Handles deposit operations.
 */
class Deposit {
    int deposit(int balance, int amount) {
        return balance + amount;
    }
}

/**
 * Handles transfer operations.
 */
class Transfer {
    int transfer(int balance, int amount) {
        return balance - amount;
    }
}

/**
 * Handles various ATM transactions like withdraw, deposit, transfer, and transaction history.
 */
class Transaction {
    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw :");
        int wcash = sc.nextInt();
        if (wcash <= ATM.balance) {
            ATM.balance -= wcash;
            ATM.history.add("Rs. " + wcash + " withdrawn");
            System.out.println("Amount Rs. " + wcash + "/- withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance to withdraw.");
        }
        ATM.prompt();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit :");
        int dcash = sc.nextInt();
        ATM.updateBalance(dcash);
        ATM.history.add("Rs. " + dcash + " deposited");
        System.out.println("Amount Rs. " + dcash + "/- deposited successfully!");
        ATM.prompt();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter recipient name:");
        String recipient = sc.nextLine();
        System.out.println("Enter account number of recipient:");
        String accNum = sc.nextLine();
        System.out.println("Enter amount to be transferred:");
        int tcash = sc.nextInt();
        if (tcash <= ATM.balance) {
            ATM.balance -= tcash;
            ATM.history.add("Rs. " + tcash + " transferred to " + recipient);
            System.out.println("Amount Rs. " + tcash + "/- transferred successfully.");
        } else {
            System.out.println("Insufficient balance to transfer.");
        }
        ATM.prompt();
    }
}

/**
 * Utility class to check the current balance of the user.
 */
class Check {
    static void checkBalance() {
        System.out.println("------------------");
        System.out.println("The available balance is: Rs. " + ATM.balance);
        System.out.println("------------------");
        ATM.prompt();
    }
}

/**
 * Displays the transaction history of the user.
 */
class History {
    static void showTransactionHistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History:");
        if (ATM.history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < ATM.history.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": " + ATM.history.get(i));
            }
        }
        System.out.println("---------------------");
        ATM.prompt();
    }
}

/**
 * Main class that manages the ATM operations.
 */
public class ATM {
    public static String name; // User's name
    public static int balance = 0; // User's account balance
    public static String accnumber; // User's account number
    public static ArrayList<String> history = new ArrayList<>(); // Transaction history list

    /**
     * Updates the user's account balance after a deposit.
     *
     * @param amount The amount deposited.
     */
    static void updateBalance(int amount) {
        balance += amount;
    }

    /**
     * Main menu displayed after registration.
     */
    public static void homepage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("--------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            BankAccount.register();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid option! Please try again.");
            homepage();
        }
    }

    /**
     * ATM system prompt for different banking operations.
     */
    static void prompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWELCOME " + ATM.name + " TO ATM SYSTEM");
        System.out.println("---------------------");
        System.out.println("Select an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> Transaction.withdraw();
            case 2 -> Transaction.deposit();
            case 3 -> Transaction.transfer();
            case 4 -> Check.checkBalance();
            case 5 -> History.showTransactionHistory();
            case 6 -> System.exit(0);
            default -> {
                System.out.println("Invalid option! Try again.");
                prompt();
            }
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ThIs class contains the driver method of the Bank System Simulator. In this class, the user is able to create an
 * account, look at the bank accounts made under the bank, check the transaction history of any bank account in the
 * list, check the account balance for any account, and make a transaction.
 *
 * @author Nora Shaipi
 * @version 1
 */
public class BankSystem {
    /**
     * The main method of the Bank System Simulator.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String args[]) {
        Bank TDBank = new Bank("TD Bank", 6, 0.1);
        Account myAccount;
        Scanner sc = new Scanner(System.in);

        //random accounts a user can access
        Account otherAccount = new Account("Other", 1500);
        TDBank.addToAccountList(otherAccount);
        Account random = new Account("Random", 200);
        TDBank.addToAccountList(random);

        System.out.println("Welcome to TD Bank! How can we help you today?");

        int userInput = options(sc);

        while (userInput < 1 || userInput > 8) {
            System.out.println("Please select one of the available options.");
            userInput = sc.nextInt();
        }

        while (userInput > 0 && userInput < 9) {
            if (userInput == 1) {
                System.out.println("To create a new account, please enter your user-name: (no spaces please)");
                String userName = "";
                boolean validUserInput = false;
                while (!validUserInput) {
                    try {
                        userName = sc.next();
                        validUserInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please make sure you entered your user-name correctly.");
                        sc.nextLine();
                    }
                }
                sc.nextLine();
                System.out.println("Please enter your starting account balance");
                double balance = 0;
                boolean validBalanceInput = false;
                while (!validBalanceInput) {
                    try {
                        balance = sc.nextDouble();
                        validBalanceInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid numeric balance.");
                        sc.nextLine();
                    }
                }
                myAccount = new Account(userName, balance);
                System.out.println("Account created successfully! Your account id is: " + myAccount.getId());
                TDBank.addToAccountList(myAccount);
                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);
            } else if (userInput == 2) {
                System.out.println("The bank accounts made under this bank are:");
                System.out.println(TDBank.accountListToString());
                userInput = options(sc);
            } else if (userInput == 3) {
                System.out.println("Type in the account's username whose transaction history you would like to check: (make sure to not make any typos");
                System.out.println(TDBank.accountListToString());
                String transactionHistory = sc.next();
                if (find(TDBank, transactionHistory)) {
                    System.out.println("The transaction history for this account is:");
                    Account found = account(TDBank, transactionHistory);
                    System.out.println(found.getTransactionList());
                } else {
                    System.out.println("Sorry, that account doesn't exist");
                }
                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);
            } else if (userInput == 4) {
                System.out.println("Type in the account's username whose transaction history you would like to check: (make sure to not make any typos");
                System.out.println(TDBank.accountListToString());
                String transactionHistory = sc.next();
                //find account under that username
                if (find(TDBank, transactionHistory)) {
                    System.out.println("The account balance for this account is:");
                    Account found = account(TDBank, transactionHistory);
                    System.out.println(found.getAccountBalance());
                } else {
                    System.out.println("Sorry, that account doesn't exist");
                }
                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);
            } else if (userInput == 5) {
                System.out.println("The list of accounts is: " + TDBank.accountListToString());
                System.out.println("Please enter the name of the account you would like to withdraw from:");
                String name = sc.next();
                if (find(TDBank, name)) {
                    Account account = account(TDBank, name); //find the account being referred to
                    System.out.println("The balance of this account is: " + account.getAccountBalance());
                    System.out.print("Please enter the amount to withdraw:");
                    double withdraw = sc.nextInt();
                    System.out.println("What is the reason for your withdrawal today?");
                    sc.nextLine();
                    String reason = sc.nextLine();
                    Transaction transaction = new Transaction(withdraw, account, reason); //create a new transaction
                    transaction.withdraw(TDBank); // perform deposit
                    account.addTransaction(transaction); // add transaction to the transaction list of accounts
                    System.out.println("Your account balance after the withdrawal is: " + account.getAccountBalance());
                } else {
                    System.out.println("Sorry, that account does not exist. Try again!");
                }
                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);
            } else if (userInput == 6) {
                System.out.println("The list of accounts is: " + TDBank.accountListToString());
                System.out.println("Please enter the name of the account you would like to make a deposit to:");
                String name = sc.next();
                if (find(TDBank, name)) {
                    Account account = account(TDBank, name); //find the account being referred to
                    System.out.println("The balance of this account is: " + account.getAccountBalance());
                    System.out.print("Please enter the amount to deposit:");
                    double deposit = sc.nextInt();
                    System.out.println("What is the reason for your deposit today?");
                    sc.nextLine();
                    String reason = sc.nextLine();
                    Transaction transaction = new Transaction(deposit, account, reason); //create a new transaction
                    transaction.deposit(TDBank); // perform deposit
                    account.addTransaction(transaction); // add transaction to the transaction list of accounts
                    System.out.println("Your account balance after the deposit is: " + account.getAccountBalance());
                } else {
                    System.out.println("Sorry, that account does not exist. Try again!");
                }
                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);
            } else if (userInput == 7) {
                System.out.println("The list of accounts is: " + TDBank.accountListToString());
                System.out.println("Please enter the name of the account you would like to transfer the money from:");
                String name1 = sc.next();
                System.out.println("Please enter the name of the account you would like to transfer the money to:");
                String name2 = sc.next();
                if (find(TDBank, name1) && find(TDBank, name2)) {
                    Account account1 = account(TDBank, name1); //find the account being referred to
                    Account account2 = account(TDBank, name2); //find other account being referred to

                    System.out.println("The balance of the originating account is: " + account1.getAccountBalance());
                    System.out.println("The balance of the resulting account is: " + account2.getAccountBalance());

                    System.out.print("Please enter the amount to transfer:");
                    double transfer = sc.nextInt();
                    System.out.println("What is the reason for your transfer today?");
                    sc.nextLine();
                    String reason = sc.nextLine();
                    Transaction transaction = new Transaction(transfer, account1, account2, reason); //create a new transaction
                    transaction.transfer(TDBank); // perform deposit
                    account1.addTransaction(transaction); // add transaction to the transaction list of accounts
                    account2.addTransaction(transaction); // add transaction to the transaction list of accounts
                    System.out.println("The originating account's balance after the transfer is: " + account1.getAccountBalance());
                    System.out.println("The resulting account's balance after the transfer is: " + account2.getAccountBalance());

                } else {
                    System.out.println("Sorry, that account does not exist. Goodbye!");
                }

                System.out.println("Would you like to perform any other of the following actions today?");
                userInput = options(sc);

            } else {
                System.out.println("Thank you for using TD Bank! Have a great day!");
                userInput = 9;
            }
        }
    }

    private static int options(Scanner sc) {
        int userInput;

        System.out.println("///////////////////////////////////////////////////");
        System.out.println("1. Create a new bank account");
        System.out.println("2. See list of bank accounts");
        System.out.println("3. Check transaction history of each bank account");
        System.out.println("4. Check account balance for any account");
        System.out.println("5. Make a withdrawal");
        System.out.println("6. Make a deposit");
        System.out.println("7. Make a transfer");
        System.out.println("8. None of the above");
        System.out.println("///////////////////////////////////////////////////");
        userInput = sc.nextInt();
        while (userInput < 1 || userInput > 8) {
            System.out.println("Please select one of the available options.");
            userInput = sc.nextInt();
        }
        return userInput;
    }

    /**
     * Checks whether a bank account exists under a specific user-name.
     *
     * @param bank is the bank in which the account is made.
     * @param str  is the user-name being checked against.
     * @return true if the account exists and false if it doesn't.
     */
    public static boolean find(Bank bank, String str) {
        for (int i = 0; i < bank.getAccountList().size(); i++) {
            if (bank.getAccountList().get(i).getUserName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a specific account made in a bank account and returns it.
     *
     * @param bank is the bank in which the account is made.
     * @param str  is the user-name being checked against.
     * @return the found account or null if the account isn't found(that should never occur).
     */
    public static Account account(Bank bank, String str) {
        Account foundAccount;
        for (int i = 0; i < bank.getAccountList().size(); i++) {
            if (bank.getAccountList().get(i).getUserName().equals(str)) {
                return foundAccount = bank.getAccountList().get(i);
            }
        }
        return null;
    }
}

import java.util.ArrayList;

/**
 * This is the Account class for the Bank System Simulator. It creates an account with a user-name and account balance,
 * adds transactions to the account list, and has methods to return the transaction list, return and set the account
 * balance, return the user's id, and return the user-name. The getters are used to ensure that the user's information
 * is saved using private access modifiers.
 *
 * @author Nora Shaipi
 * @version 1
 */
public class Account {
    private static int nextAccountId = 1; // Static variable to track the next available account ID
    private int id;
    private String userName;
    private double accountBalance;
    private ArrayList<Transaction> transactionList = new ArrayList<>();

    /**
     * The constructor for the account class.
     *
     * @param userName       is the user-name of the user
     * @param accountBalance is the account balance of the user
     */
    public Account(String userName, double accountBalance) {
        this.id = nextAccountId++;
        this.userName = userName;
        this.accountBalance = accountBalance;
    }

    /**
     * Sets the account balance for a user.
     *
     * @param accountBalance is the value the account balance of the user gets set to.
     */
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Returns the account balance.
     *
     * @return the account balance of a user.
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Returns the id.
     *
     * @return the id of a user's profile.
     */
    public int getId() {
        return id;
    }

    /**
     * Adds a transaction to the user's transaction list.
     *
     * @param transaction is the transaction to be added.
     */
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    /**
     * Returns a concatenated, comma-separated list of transactions a user has made.
     *
     * @return the concatenated String of a user's transactions.
     */
    public String getTransactionList() {
        String str = "";
        if (transactionList.isEmpty()) {
            str += "No transactions have been made under this account yet";
        }
        for (int i = 0; i < transactionList.size(); i++) {
            str += transactionList.get(i).transactionToString();
            if (i + 1 < transactionList.size()) {
                str += ", ";
            }
        }
        return str;
    }

    /**
     * Return the user's user-name.
     *
     * @return the user's user-name.
     */
    public String getUserName() {
        return userName;
    }
}

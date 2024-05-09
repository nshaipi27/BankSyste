import java.util.ArrayList;

/**
 * This is the Bank class of the Bank System Simulator. It creates a bank and sets the bank's flat-fee, as well as
 * percent fee. It includes methods to add an account to the list of accounts under that bank, checks whether an
 * account exists, returns a comma-separated list of all the accounts under that bank, and methods to return other
 * instance variables that must be kept private.
 *
 * @author Nora Sahipi
 * @version 1
 */
public class Bank {
    public String name;
    private ArrayList<Account> accountList = new ArrayList<>();
    private double flatTransactionFee;
    private double transactionPercent;

    /**
     * The constructor for the Bank class.
     *
     * @param name               is the bank's name.
     * @param flatTransactionFee is the flat transaction fee the bank charges.
     * @param transactionPercent is the percent transaction fee the bank charges.
     */
    public Bank(String name, double flatTransactionFee, double transactionPercent) {
        this.name = name;
        this.flatTransactionFee = flatTransactionFee;
        this.transactionPercent = transactionPercent;
    }

    /**
     * Adds an account to the list of accounts made under a bank.
     *
     * @param account is the account to be added.
     */
    public void addToAccountList(Account account) {
        accountList.add(account);
    }


    /**
     * Returns a concatenated, comma-separated String of all the accounts created under the bank.
     *
     * @return a String of the accounts created under the bank.
     */
    public String accountListToString() {
        String str = "";
        for (int i = 0; i < accountList.size(); i++) {
            str += accountList.get(i).getUserName();
            if (i + 1 < accountList.size()) {
                str += ", ";
            }
        }
        return str;
    }

    /**
     * Returns the list of accounts as is.
     *
     * @return a list of the accounts created under the bank.
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    /**
     * Returns the flat fee that the bank charges.
     *
     * @return the bank's flat fee.
     */
    public double getFlatFee() {
        return flatTransactionFee;
    }

    /**
     * Returns the percent fee that the bank charges.
     *
     * @return the bank's percent fee.
     */
    public double getPercentFee() {
        return transactionPercent;
    }

}

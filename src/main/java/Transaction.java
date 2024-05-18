/**
 * This is the Transaction class. A transaction can be a withdrawal, deposit, or transfer from one account to another.
 * This class contains methods for calculating the fees, and performing withdrawal's, deposit's, and transfers.
 *
 * @author Nora Shaipi
 * @version 1
 */
public class Transaction {
    private double amount;
    private Account originatingAccount;
    private Account resultingAccount;
    private String transactionReason;
    private double transactionTax;

    /**
     * The constructor for the Transaction class. This constructor is used for withdrawal's/deposits since information
     * regarding another account is not necessary.
     *
     * @param amount             is the amount of the withdrawal/deposit.
     * @param originatingAccount is the account withdrawing/depositing.
     * @param transactionReason  is the reason for the transaction.
     */
    public Transaction(double amount, Account originatingAccount, String transactionReason) {
        this.amount = amount;
        this.originatingAccount = originatingAccount;
        this.transactionReason = transactionReason;
    }

    /**
     * The constructor for the Transaction class. This constructor is used for transfers from one account to another,
     * which is why it contains information for a second account.
     *
     * @param amount             is the amount to be transferred.
     * @param originatingAccount is the account where the amount is taken from.
     * @param resultingAccount   is the account where the amount is transferred to.
     * @param transactionReason  is the reason for the transaction.
     */

    public Transaction(double amount, Account originatingAccount, Account resultingAccount, String transactionReason) {
        this.amount = amount;
        this.originatingAccount = originatingAccount;
        this.resultingAccount = resultingAccount;
        this.transactionReason = transactionReason;
    }
    private double calculateTransactionFee(Bank bank) {
        if (amount < 50) { //perform flat fee
            transactionTax = bank.getFlatFee();
        } else { //perform percent fee
            transactionTax = (amount * bank.getPercentFee());
        }
        return transactionTax;
    }

    /**
     * Withdraws money from a bank account.
     *
     * @param bank is the bank in which the account is created under.
     */
    public void withdraw(Bank bank) {
        //balance 1 gets taxed
        double balance1 = originatingAccount.getAccountBalance();
        if (balance1 - (amount + calculateTransactionFee(bank)) >= 0) {
            originatingAccount.setAccountBalance(balance1 -= (amount + calculateTransactionFee(bank)));
        } else {
            System.out.println("Insufficient funds. Try again!");
        }
    }

    /**
     * Deposits money into a bank account.
     *
     * @param bank is the bank in which the account is created under.
     */
    public void deposit(Bank bank) {
        double balance1 = originatingAccount.getAccountBalance();
        originatingAccount.setAccountBalance(balance1 += (amount - calculateTransactionFee(bank)));
    }

    /**
     * Transfers money from one account to another.
     *
     * @param bank is the bank in which the transfer occurs in.
     */
    public void transfer(Bank bank) {
        double balance1 = originatingAccount.getAccountBalance();
        double balance2 = resultingAccount.getAccountBalance();
        //balance 1 gets taxed
        if (balance1 - ((amount + calculateTransactionFee(bank))) >= 0) {
            originatingAccount.setAccountBalance(balance1 -= (amount + calculateTransactionFee(bank)));
            resultingAccount.setAccountBalance(balance2 += amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    /**
     * Returns a transaction as a String represented by its amount and transaction reason.
     *
     * @return a concatenated comma-separated string of the amount and transaction reason.
     */
    public String transactionToString() {
        return "Amount: " + amount + ", Transaction reason: " + transactionReason;
    }
}

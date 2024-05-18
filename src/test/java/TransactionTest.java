import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Test class for Transaction.
 *
 * @author Nora Shaipi
 * @version 1
 */
class TransactionTest {

    /**
     * Test withdraw method.
     */
    @Test
    @DisplayName("Test withdraw method for Transaction class")
    public void testWithdraw() {
        double amount = 100;
        Account originatingAccount = new Account("nora", 15000);
        String transactionReason = "This is a transaction";
        Transaction transaction = new Transaction(amount, originatingAccount, transactionReason);
        Bank bank = new Bank("Random bank", 10, 0.5);

        transaction.withdraw(bank);
        assertEquals(14850, originatingAccount.getAccountBalance());
    }

    /**
     * Test deposit method.
     */
    @Test
    @DisplayName("Test depost method for Transaction class")
    public void testDeposit() {
        double amount = 100;
        Account originatingAccount = new Account("nora", 15000);
        String transactionReason = "This is a transaction";
        Transaction transaction = new Transaction(amount, originatingAccount, transactionReason);
        Bank bank = new Bank("Random bank", 10, 0.5);

        transaction.deposit(bank);
        assertEquals(15050, originatingAccount.getAccountBalance());
    }

    /**
     * Test transfer method.
     */
    @Test
    @DisplayName("Test transfer method for Transaction class")
    public void testTransfer() {
        double amount = 100;
        Account originatingAccount = new Account("nora", 15000);
        Account resultingAccount = new Account("diana", 10000);
        String transactionReason = "This is a transaction";
        Transaction transaction = new Transaction(amount, originatingAccount, resultingAccount, transactionReason);
        Bank bank = new Bank("Random bank", 10, 0.5);

        transaction.transfer(bank);
        assertEquals(14850, originatingAccount.getAccountBalance());
        assertEquals(10100, resultingAccount.getAccountBalance());
    }
}
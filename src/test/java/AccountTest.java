import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for Account.
 *
 * @author Nora Shaipi
 * @version 1
 */
class AccountTest {
    /**
     * Test the add transaction method.
     */
    @Test
    @DisplayName("Test add transaction method")
    public void testAddTransaction() {
        Account account = new Account("nora", 1000);
        Transaction transaction = new Transaction(100, account, "reason");
        Bank bank = new Bank("Bank", 10, 0.1);

        transaction.withdraw(bank);
        account.addTransaction(transaction);

        assertEquals("Amount: 100.0, Transaction reason: reason", account.getTransactionList());
    }
}
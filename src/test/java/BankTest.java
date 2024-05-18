import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * This is the Test class for the Bank.
 *
 * @author Nora Shaipi
 * @version 1
 */
class BankTest {

    /**
     * Test the constructor for the Bank class.
     */
    @Test
    @DisplayName("Test the Bank constructor")
    public void testConstructor() {
        String bankName = "Random Bank";
        double fee1 = 10;
        double fee2 = 0.2;
        Bank bank = new Bank(bankName, fee1, fee2);

        //Make sure that everything was set correctly.
        assertTrue(bank.name.equals("Random Bank"));
        assertTrue(bank.getFlatFee() == 10);
        assertTrue(bank.getPercentFee() == 0.2);
    }

    /**
     * Test the Bank's addToAccountList method.
     */
    @Test
    @DisplayName("Test the addToAccountList() method ")
    public void testAddToAccountList() {
        String bankName = "Random Bank";
        double fee1 = 10;
        double fee2 = 0.2;
        Bank bank = new Bank(bankName, fee1, fee2);

        //Create two new accounts.
        Account account1 = new Account("norashaipi", 12000);
        Account account2 = new Account("dianashaipi", 10000);

        //Add the accounts to the bank's list.
        bank.addToAccountList(account1);
        bank.addToAccountList(account2);

        //Check that the accounts were added to the bank's list.
        assertTrue(bank.accountListToString().equals("norashaipi, dianashaipi"));
    }
}
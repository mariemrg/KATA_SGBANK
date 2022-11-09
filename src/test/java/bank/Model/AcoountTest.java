package bank.Model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AcoountTest {

    @Test
    public void acountTestWithNullParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Account(null));
    }

    @Test
    public void accountTestWithNagtiveIntialAmount(){
        assertThrows(IllegalArgumentException.class, () -> new Account(new BigDecimal("-765")));
    }


    @Test
    public void withDrawTestWithNegativeAmount() {
        // given
        Account account = new Account(new BigDecimal("1000"));
        // then
       assertThrows(IllegalArgumentException.class, () -> account.withDraw(new BigDecimal("-20")));
    }

    @Test
    public void withDrawTestWithNegativeWithDraw() {
        // given
        Account account = new Account(new BigDecimal("100"));
        // then
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-20")));
    }

    @Test
   public void accountTestCountBalanceWithDepositAmount() {
        // given
        Account account = new Account(new BigDecimal("800"));
        // when
        account.deposit(new BigDecimal("200"));
        // then
        assertEquals(new BigDecimal("1000"), account.getBalance());
    }

    @Test
    public void accountTestCountBalanceWithDrawAmount() {
        // given
        Account account = new Account(new BigDecimal("800"));
        // when
        account.withDraw(new BigDecimal("200"));
        // then
        assertEquals(new BigDecimal("600"), account.getBalance());
    }
}

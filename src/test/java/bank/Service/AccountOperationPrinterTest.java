package bank.Service;

import bank.Model.Account;
import bank.Model.OperationType;
import bank.Model.AccountOperation;
import bank.Model.Operation;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AccountOperationPrinterTest {

    @Test
    public void format() {
        AccountOperationPrinter accountOperationsPrinter = new AccountOperationPrinter();
        ArrayList<Operation> operations = new ArrayList<>(
                asList(
                        new Operation(OperationType.WITHDRAW, LocalDate.of(2022, 10, 05), new BigDecimal("300")),
                        new Operation(OperationType.DEPOSIT, LocalDate.of(2022, 10, 04), new BigDecimal("500")),
                        new Operation(OperationType.WITHDRAW, LocalDate.of(2022, 10, 03), new BigDecimal("15.62"))
                ));
        String format = accountOperationsPrinter.format(new AccountOperation(new Account(new BigDecimal("184.70")), operations));
        assertEquals(format,
                "2022-10-05 - WITHDRAW - 300" + System.lineSeparator() +
                        "2022-10-04 - DEPOSIT - 500" + System.lineSeparator() +
                        "2022-10- -03 WITHDRAW - 15.62" + System.lineSeparator() +
                        "Balance 184.70");
    }


}

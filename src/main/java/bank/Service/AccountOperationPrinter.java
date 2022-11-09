package bank.Service;

import bank.Model.Account;
import bank.Model.Operation;
import bank.Model.AccountOperation;

import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class AccountOperationPrinter {

    public static final String SEPARATOR = " - ";

    public String format(AccountOperation accountOperation) {
        String formatAccount = format(accountOperation.getAccount());
        String formatOperations = format(accountOperation.getOperations());
        return formatOperations + lineSeparator() + formatAccount;
    }

    private String format(List<Operation> operations) {
        return operations
                .stream()
                .map(this::formatOperation)
                .collect(joining(lineSeparator()));
    }

    private String formatOperation(Operation o) {
        return o.getDate().toString()
                + SEPARATOR
                + o.getOperationType()
                + SEPARATOR
                + o.getAmount();
    }

    private String format(Account account) {
        return "Balance " + account.getBalance().toString();
    }
}

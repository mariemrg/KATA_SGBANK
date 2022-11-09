package bank.Service;

import bank.Model.Account;
import bank.Model.Operation;
import bank.Model.OperationType;
import bank.Repository.OperationRepository;
import bank.Repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AccountManager {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountManager(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    public void withDraw(Account account, BigDecimal amount) {
        account.withDraw(amount);
        accountRepository.saveAccount(account);
        operationRepository.addOperation(account, new Operation(OperationType.WITHDRAW, LocalDate.now(), amount));
    }

    public void deposit(Account account, BigDecimal amount) {
        account.deposit(amount);
        accountRepository.saveAccount(account);
        operationRepository.addOperation(account, new Operation(OperationType.DEPOSIT, LocalDate.now(), amount));
    }

    public List<Operation> consultOperationsHistory(Account account) {
        return operationRepository.getHistory(account);
    }
}

package bank.Service;

import bank.Model.Account;
import bank.Model.Operation;
import bank.Model.OperationType;
import bank.Repository.AccountRepository;
import bank.Repository.OperationRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountManagerTest {


    @Test
   public void whithDraw() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        AccountManager accountManager = new AccountManager(accountRepository, getOperationHistoryRepository());
        //when
        accountManager.withDraw(account, ONE);
        //then
        Account accountFinal = accountRepository.getAccount();
        assertNotNull(accountFinal);
        assertEquals(accountFinal.getBalance(),new BigDecimal("9"));
    }


    @Test
    public void deposit() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        AccountManager accountManager = new AccountManager(accountRepository, getOperationHistoryRepository());
        //when
        accountManager.deposit(account, ONE);
        //then
        Account accountFinal = accountRepository.getAccount();
        assertNotNull(accountFinal);
        assertEquals(accountFinal.getBalance(),new BigDecimal("11"));
    }

    @Test
    public void consultOperationsHistory() {
        // given
        AccountRepository accountRepository = getAccountRepository(BigDecimal.TEN);
        Account account = accountRepository.getAccount();
        OperationRepository operationRepository = getOperationHistoryRepository();
        AccountManager accountManager = new AccountManager(accountRepository, operationRepository);

        // when
        accountManager.deposit(account, ONE);
        accountManager.withDraw(account, new BigDecimal("2"));
        accountManager.deposit(account, new BigDecimal("3"));

        // then
        List<Operation> history = accountManager.consultOperationsHistory(account);
        assertNotNull(history);
        assertEquals(history.size(),3);
        assertEquals(history.get(0),(new Operation(OperationType.DEPOSIT, now(), ONE)));
        assertEquals(history.get(1) , (new Operation(OperationType.WITHDRAW, now(), new BigDecimal("2"))));
        assertEquals(history.get(2) , (new Operation(OperationType.DEPOSIT, now(), new BigDecimal("3"))));
    }


    private AccountRepository getAccountRepository(BigDecimal initialDefaultValue) {
        return new AccountRepository() {
            private Account account = new Account(initialDefaultValue);
            @Override
            public Account getAccount() {
                return this.account;
            }

            @Override
            public void saveAccount(Account account) {
                this.account = account;
            }
        };
    }

    private OperationRepository getOperationHistoryRepository() {
        return new OperationRepository() {
            private List<Operation> operationHistory = new ArrayList<>();
            @Override
            public List<Operation> getHistory(Account account) {
                return this.operationHistory;
            }

            @Override
            public void addOperation(Account account, Operation operation) {
                this.operationHistory.add(operation);
            }
        };
    }
}

package bank.Repository;

import bank.Model.Account;

public interface AccountRepository {

    Account getAccount();

    void saveAccount(Account account);
}

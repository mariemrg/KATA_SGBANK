package bank.Repository;

import bank.Model.Account;
import bank.Model.Operation;

import java.util.List;

public interface OperationRepository {

    List<Operation> getHistory(Account account) ;

    void addOperation(Account account, Operation operation);
}

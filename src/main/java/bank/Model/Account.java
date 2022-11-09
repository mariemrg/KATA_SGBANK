package bank.Model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance ;

    public Account(BigDecimal initialAmount) {
        verifAmount(initialAmount, "Initial amount can not be null and must be positive");
        this.balance = initialAmount;
    }

    public BigDecimal deposit(BigDecimal amount) {
        verifAmount(amount, "Deposit amount can not be null and must be positive");
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public BigDecimal withDraw(BigDecimal amount) {
        verifAmount(amount, "WithDraw can not be null and must be positive");
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    private void verifAmount(BigDecimal initialAmount, String message) {
        if (initialAmount == null || initialAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}

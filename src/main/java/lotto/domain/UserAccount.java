package lotto.domain;

public class UserAccount {

    private int balance;

    public UserAccount(int balance) {
        validateBalance(balance);
        this.balance = balance;
    }

    public void withdraw(int money) {
        balance -= money;
    }

    public boolean hasEnoughMoney(int money) {
        return this.balance >= money;
    }

    private void validateBalance(int balance) {
        if (balance < 0) {
            throw new IllegalStateException("잔액은 0이상이여야 합니다.");
        }
    }
}

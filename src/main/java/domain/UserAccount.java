package domain;

public class UserAccount {

    private final int balance;

    public UserAccount(int balance) {
        this.balance = balance;
        validateBalance();
    }

    public UserAccount withdraw(int money) {
        return new UserAccount(balance - money);
    }

    public boolean hasEnoughMoney(int money) {
        return this.balance >= money;
    }

    private void validateBalance() {
        if (this.balance < 0) {
            throw new IllegalStateException("잔액은 0이상이여야 합니다.");
        }
    }
}

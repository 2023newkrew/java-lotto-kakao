package lotto.domain;

public class UserAccount {

    private int balance;

    public UserAccount(int balance) {
        validateBalance(balance);
        this.balance = balance;
    }
    
    public int getBalance() {
        return balance;
    }

    public void withdraw(int money) {
        balance -= money;
    }

    private void validateBalance(int balance) {
        if (balance < 0) {
            throw new IllegalStateException("잔액은 0이상이여야 합니다.");
        }
    }

}

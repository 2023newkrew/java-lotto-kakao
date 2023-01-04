package buyer;

public class Money {
    private int money;

    public Money(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public boolean isMoreThan(int number) {
        return money >= number;
    }

    public void decreaseMoney(int number) {
        if (this.money < number) {
            throw new RuntimeException("충분한 돈이 없습니다!");
        }
        this.money -= number;
    }
}

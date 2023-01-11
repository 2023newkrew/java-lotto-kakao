package model;

public class Money {
    protected long money;

    public Money() {
        money = 0;
    }

    public Money(long money) {
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

}

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

    public void add(Money money) {
        this.money += money.getMoney();
    }

    public void add(Money money, int multiply) {
        this.money += money.getMoney() * multiply;
    }

    public double rate(Money money) {
        return (double)this.money / money.getMoney();
    }
}

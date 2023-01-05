package lotto.model;

public enum Price {
    FIRST("6개 일치",2000000000),
    SECOND("5개 일치, 보너스 볼 일치",30000000),
    THIRD("5개 일치", 1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000),
    NOTHING("", 0);

    private final String matchDescription;
    private final long price;

    Price(String matchDescription, long price) {
        this.matchDescription = matchDescription;
        this.price = price;
    }

    public String matchDescription(){
        return matchDescription;
    }

    public long price(){
        return price;
    }
}

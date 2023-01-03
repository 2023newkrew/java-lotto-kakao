package lotto.model;

public enum Prize {
    LOSING("", 0L),
    FIFTH("3개 일치", 5000L),
    FOURTH("4개 일치", 50000L),
    THIRD("5개 일치", 1500000L),
    SECOND("5개 일치, 보너스 볼 일치",30000000L),
    FIRST("6개 일치",2000000000L);

    private final String matchDescription;
    private final long prize;

    Prize(String matchDescription, long prize) {
        this.matchDescription = matchDescription;
        this.prize = prize;
    }

    public String matchDescription(){
        return matchDescription;
    }

    public long prize(){
        return prize;
    }
}

package lotto.model.prize;

public enum Prize {
    LOSING("", 0L),
    FIFTH("3개 일치", 5_000L),
    FOURTH("4개 일치", 50_000L),
    THIRD("5개 일치", 1_500_000L),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000L),
    FIRST("6개 일치", 2_000_000_000L);

    private final String matchDescription;
    private final long prize;

    Prize(String matchDescription, long prize) {
        this.matchDescription = matchDescription;
        this.prize = prize;
    }

    public String matchDescription() {
        return matchDescription;
    }

    public long prize() {
        return prize;
    }
}

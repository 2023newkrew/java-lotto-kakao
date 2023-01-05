package lotto.domain.enumeration;

public enum LottoResult {
    FIFTH(3,5000, "%d개 일치 (%d원)"),
    FOURTH(4,50000, "%d개 일치 (%d원)"),
    THIRD(5,1500000, "%d개 일치 (%d원)"),
    SECOND(5,30000000, "%d개 일치, 보너스 볼 일치(%d원)"),
    FIRST(6,2000000000, "%d개 일치 (%d원)"),
    MISS(0,0, "꽝");

    private final int numberCount;
    private final int prize;
    private final String stringFormat;

    LottoResult(int numberCount, int prize, String stringFormat) {
        this.numberCount = numberCount;
        this.prize = prize;
        this.stringFormat = stringFormat;
    }

    public static LottoResult getLottoResultOf(int numberCount, boolean hasBonusNumber) {
        if (numberCount == 6) { return FIRST; }
        if (numberCount == 5 && hasBonusNumber) { return SECOND; }
        if (numberCount == 5 ) { return THIRD; }
        if (numberCount == 4) { return FOURTH; }
        if (numberCount == 3) { return FIFTH; }
        return MISS;
    }

    public String getString() {

        return String.format(stringFormat, numberCount, prize);
    }

    public int getPrize() {
        return prize;
    }
}

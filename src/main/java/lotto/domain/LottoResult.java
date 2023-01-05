package lotto.domain;

import java.util.Arrays;

public enum LottoResult {
    FIFTH(3, false, false,5000, "%d개 일치 (%d원)"),
    FOURTH(4, false, false,50000, "%d개 일치 (%d원)"),
    THIRD(5, false, true, 1500000, "%d개 일치 (%d원)"),
    SECOND(5, true, true, 30000000, "%d개 일치, 보너스 볼 일치(%d원)"),
    FIRST(6, false, false,2000000000, "%d개 일치 (%d원)"),
    MISS(0, false, false,0, "꽝");

    private final int numberCount;
    private final boolean bonusNumber;
    private final boolean checkBonusNumber;
    private final int prize;
    private final String stringFormat;

    LottoResult(int numberCount, boolean bonusNumber, boolean checkBonusNumber,
            int prize, String stringFormat) {
        this.numberCount = numberCount;
        this.bonusNumber = bonusNumber;
        this.checkBonusNumber = checkBonusNumber;
        this.prize = prize;
        this.stringFormat = stringFormat;
    }

    public static LottoResult of(int numberCount, boolean bonusNumber) {
        return Arrays.stream(values())
                .filter(it -> it.numberCount == numberCount)
                .filter(it -> !it.checkBonusNumber || it.bonusNumber == bonusNumber)
                .findFirst().orElse(MISS);
    }

    public String getString() {
        return String.format(stringFormat, numberCount, prize);
    }

    public int getPrize() {
        return prize;
    }
}


package lotto.domain.enumeration;

import java.util.Arrays;

public enum LottoResult {
    FIFTH(3, false, 5000, "%d개 일치 (%d원)"),
    FOURTH(4, false, 50000, "%d개 일치 (%d원)"),
    THIRD(5, false, 1500000, "%d개 일치 (%d원)"),
    SECOND(5, true, 30000000, "%d개 일치, 보너스 볼 일치(%d원)"),
    FIRST(6, false, 2000000000, "%d개 일치 (%d원)"),
    MISS(0, false, 0, "꽝");

    private final int numberCount;
    private final boolean bonusNumber;
    private final int prize;
    private final String stringFormat;

    LottoResult(int numberCount, boolean bonusNumber, int prize, String stringFormat) {
        this.numberCount = numberCount;
        this.bonusNumber = bonusNumber;
        this.prize = prize;
        this.stringFormat = stringFormat;
    }

    public static LottoResult getLottoResultOf(int numberCount, boolean bonusNumber) {
        return Arrays.stream(values())
                .filter(it -> it.numberCount == numberCount)
                .filter(it -> it.bonusNumber == bonusNumber)
                .findFirst().orElse(MISS);
    }

    public String getString() {

        return String.format(stringFormat, numberCount, prize);
    }

    public int getPrize() {
        return prize;
    }
}

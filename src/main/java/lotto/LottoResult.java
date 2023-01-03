package lotto;

import java.util.Arrays;

public enum LottoResult {
    FIRST(6, false),
    SECOND(5, true),
    THIRD(5, false),
    FOURTH(4, false),
    FIFTH(3, false),
    MISS(0, false);

    private final int numberCount;
    private final boolean bonusNumber;

    LottoResult(int numberCount, boolean bonusNumber) {
        this.numberCount = numberCount;
        this.bonusNumber = bonusNumber;
    }

    public static LottoResult getLottoResultOf(int numberCount, boolean bonusNumber) {
        return Arrays.stream(values())
                .filter(it -> it.numberCount == numberCount)
                .filter(it -> it.bonusNumber == bonusNumber)
                .findFirst().orElse(MISS);
    }
}

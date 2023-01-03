package lotto.model.enums;

import java.util.Arrays;

public enum LottoResultType {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NONE(0, 0);

    static public LottoResultType findLottoResult(Integer matchCount, boolean includeBonus) {
        if (matchCount == 5 && !includeBonus) {
            return LottoResultType.THIRD;
        }

        return Arrays.stream(values())
                .filter(lottoResult -> lottoResult.matchCount == matchCount)
                .findFirst()
                .orElse(LottoResultType.NONE);
    }

    private final long prize;
    private final int matchCount;

    LottoResultType(long prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}

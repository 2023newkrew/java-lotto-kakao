package lotto.domain;

import static lotto.config.LottoConfig.*;

public enum LottoRank {
    FIRST(LOTTO_SIZE, 2_000_000_000),
    SECOND(LOTTO_SIZE - 1, 30_000_000),
    THIRD(LOTTO_SIZE - 1, 1_500_000),
    FOURTH(LOTTO_SIZE - 2, 50_000),
    FIFTH(LOTTO_SIZE - 3, 5_000),
    FAIL(0, 0);

    public final int count;
    public final long prize;

    LottoRank(int count, long prize) {
        this.count = count;
        this.prize = prize;
    }

    public static LottoRank getRank(Integer matchCount, Boolean matchedBonus) {
        if (matchCount == LottoRank.FIRST.count) {
            return LottoRank.FIRST;
        } else if (matchCount == LottoRank.SECOND.count && matchedBonus) {
            return LottoRank.SECOND;
        } else if (matchCount == LottoRank.THIRD.count) {
            return LottoRank.THIRD;
        } else if (matchCount == LottoRank.FOURTH.count) {
            return LottoRank.FOURTH;
        } else if (matchCount == LottoRank.FIFTH.count) {
            return LottoRank.FIFTH;
        }
        return LottoRank.FAIL;
    }
}

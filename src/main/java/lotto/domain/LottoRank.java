package lotto.domain;

import java.util.Arrays;

public enum LottoRank {

    FIRST(1, 6, false, 2_000_000_000),
    SECOND(2, 5, true, 30_000_000),
    THIRD(3, 5, false, 1_500_000),
    FOURTH(4, 4, false, 50_000),
    FIFTH(5, 3, false, 5_000),
    NOTHING(6, 2, false, 0),
    ;

    private final int rank;
    private final int matchCount;
    private final boolean requiredBonus;
    private final long winning;

    LottoRank(int rank, int matchCount, boolean requiredBonus, long winning) {
        this.rank = rank;
        this.matchCount = matchCount;
        this.requiredBonus = requiredBonus;
        this.winning = winning;
    }

    public static LottoRank caculateLottoRank(int matchCount, boolean requiredBonus) {
        return Arrays.stream(LottoRank.values()).filter(lottoRank -> {
            if (lottoRank.requiredBonus) {
                return lottoRank.matchCount == matchCount && requiredBonus;
            }
            return lottoRank.matchCount == matchCount;
        }).findFirst().orElse(NOTHING);
    }

    public static String getLottoRankString(LottoRank lottoRank, int count) {
        if (lottoRank.requiredBonus) {
            return requiredBonusString(lottoRank, count);
        }
        if(lottoRank == NOTHING){
            return "";
        }
        return String.format("%d개 일치 (%d원)- %d개", lottoRank.matchCount, lottoRank.winning, count);
    }

    private static String requiredBonusString(LottoRank lottoRank, int count) {
        return String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", lottoRank.matchCount, lottoRank.winning, count);
    }

    public int getRank() {
        return rank;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isRequiredBonus() {
        return requiredBonus;
    }

    public long getWinning() {
        return winning;
    }
}

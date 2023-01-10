package domain;

import java.util.Arrays;

public enum LottoRank {
    DEFAULT(0, false, 0),
    FIFTH(3, false, 5_000),
    FIFTH_BONUS(3, true, 5_000),
    FOURTH(4, false, 50_000),
    FOURTH_BONUS(4, true, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6,false,  2_000_000_000);

    private final int matchCount;
    private final int prizeMoney;
    private final boolean isBonusNumberMatched;

    LottoRank(int matchCount, boolean isBonusNumberMatched, int prizeMoney){
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonusNumberMatched() {
        return isBonusNumberMatched;
    }

    public static LottoRank from(LottoMatchResult lottoMatchResult){
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> (lottoRank.getMatchCount() == lottoMatchResult.getMatchCount()))
                .filter(lottoRank -> (lottoRank.isBonusNumberMatched() == lottoMatchResult.isBonusNumberMatched()))
                .findFirst()
                .orElse(LottoRank.DEFAULT);
    }
}
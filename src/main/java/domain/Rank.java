package domain;

import java.util.Arrays;

import static domain.Rank.BonusBallMatchType.*;

public enum Rank {

    FIRST_PLACE(6, NOT_APPLICABLE, 2_000_000_000),
    SECOND_PLACE(5, MATCH, 30_000_000),
    THIRD_PLACE(5, NOT_MATCH, 1_500_000),
    FOURTH_PLACE(4, NOT_APPLICABLE, 50_000),
    FIFTH_PLACE(3, NOT_APPLICABLE, 5_000),
    ;

    private int matchCount;
    private BonusBallMatchType bonusBallMatchType;
    private long prize;

    Rank(int matchCount, BonusBallMatchType bonusBallMatchType, int prize) {
        this.matchCount = matchCount;
        this.bonusBallMatchType = bonusBallMatchType;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Rank findRank(int matchCount, boolean isMatchWithBonusBall) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.bonusBallMatchType.matchBonusBallIfMatchable(isMatchWithBonusBall))
                .findAny()
                .orElse(null);
    }

    enum BonusBallMatchType {
         NOT_APPLICABLE {
            boolean matchBonusBallIfMatchable(boolean isMatchWithBonusBall) {
                return true;
            }
        },
        NOT_MATCH {
            boolean matchBonusBallIfMatchable(boolean isMatchWithBonusBall) {
                return !isMatchWithBonusBall;
            }
        },
        MATCH {
            boolean matchBonusBallIfMatchable(boolean isMatchWithBonusBall) {
                return isMatchWithBonusBall;
            }
        },
        ;

        abstract boolean matchBonusBallIfMatchable(boolean isMatchWithBonusBall);
    }

}

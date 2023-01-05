package lotto.models.enums;

import static lotto.models.enums.MatchType.*;

import java.util.Arrays;
import lotto.models.LotteryStatistics;

public enum Rank {

    NONE(0, 0, IRRELEVANT),
    FIFTH(5_000, 3, IRRELEVANT),
    FOURTH(50_000, 4, IRRELEVANT),
    THIRD(1_500_000, 5, INCONSISTENCY),
    SECOND(30_000_000, 5, MATCH),
    FIRST(2_000_000_000, 6, INCONSISTENCY);

    private final long prize;

    private final int matchCount;

    private final MatchType includeBonus;

    Rank(long prize, int matchCount, MatchType includeBonus) {
        this.prize = prize;
        this.matchCount = matchCount;
        this.includeBonus = includeBonus;
    }

    public long getPrize() {
        return prize;
    }

    public String getWinningCountString(LotteryStatistics statistics) {
        if (this == Rank.NONE) {
            return "---------";
        }
        StringBuilder result = new StringBuilder(matchCount + "개 일치");
        if (this.includeBonus == MATCH) {
            result.append( ", 보너스 볼 일치");
        }
        result.append(" (").append(prize).append("원) - ").append(statistics.getCountOf(this)).append("개");
        return result.toString();
    }

    static public Rank findRank(Integer matchCount, boolean includeBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match(matchCount, includeBonus))
                .findFirst()
                .orElse(Rank.NONE);
    }

    private boolean match(Integer matchCount, boolean includeBonus) {
        return this.matchCount == matchCount && this.includeBonus.pass(includeBonus);
    }
}

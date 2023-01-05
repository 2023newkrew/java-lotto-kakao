package lotto.models.enums;

import java.util.Arrays;
import lotto.models.LotteryStatistics;

public enum Rank {

    NONE(0, 0, false),
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final long prize;

    private final int matchCount;

    private final boolean includeBonus;

    Rank(long prize, int matchCount, boolean includeBonus) {
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
        if (this.includeBonus) {
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
        if (matchCount != this.matchCount) {
            return false;
        }
        if (matchCount != 5) {
            return true;
        }
        return includeBonus == this.includeBonus;
    }
}

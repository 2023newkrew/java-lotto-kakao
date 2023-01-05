package lotto.domain.rank;

import static lotto.constant.LotteryConstant.LOTTERY_MATCH_COUNT_MINIMUM;
import static lotto.domain.rank.IsBonusRequired.TRUE;
import static lotto.domain.rank.IsBonusRequired.FALSE;
import static lotto.domain.rank.IsBonusRequired.IRRELEVANT;


public enum LotteryRank {
    FIRST(0, 2_000_000_000, new RankCriteria(6, 6, IRRELEVANT)),
    SECOND(1, 30_000_000, new RankCriteria(5, 5, TRUE)),
    THIRD(2, 1_500_000, new RankCriteria(5, 5, FALSE)),
    FOURTH(3, 50_000, new RankCriteria(4, 4, IRRELEVANT)),
    FIFTH(4, 5_000, new RankCriteria(3, 3, IRRELEVANT)),
    DEFAULT(5, 0, new RankCriteria(LOTTERY_MATCH_COUNT_MINIMUM, 2, IRRELEVANT));

    private final int index;
    private final RankCriteria rankCriteria;
    private final long prize;

    public int getIndex() {
        return index;
    }

    public RankCriteria getRankCriteria() {
        return rankCriteria;
    }

    public long getPrize() {
        return prize;
    }

    public int getMinMatchCount() {
        return rankCriteria.getMinMatchCount();
    }

    public IsBonusRequired isBonusRequired() {
        return rankCriteria.isBonusRequired();
    }

    LotteryRank(int index, long prize, RankCriteria rankCriteria) {
        this.index = index;
        this.prize = prize;
        this.rankCriteria = rankCriteria;
    }
}

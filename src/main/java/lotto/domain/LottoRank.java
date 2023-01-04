package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_MIN_MATCH_COUNT;

public enum LottoRank {
    FIRST(0, 2_000_000_000, 6, false),
    SECOND(1, 30_000_000, 5, true),
    THIRD(2, 1_500_000, 5, false),
    FOURTH(3, 50_000, 4, false),
    FIFTH(4, 5_000, 3, false),
    DEFAULT(5, 0, LOTTO_MIN_MATCH_COUNT, false);

    private final int index;
    private final int minMatchCount;
    private final boolean isRequiresBonus;
    private final RankCriteria rankCriteria;
    private final long winning;

    public int index() {
        return index;
    }

    public int minMatchCount() {
        return minMatchCount;
    }

    public boolean isRequiresBonus() {
        return isRequiresBonus;
    }

    public RankCriteria rankCritera() {
        return rankCriteria;
    }

    public long winning() {
        return winning;
    }

    LottoRank(int index, long winning, int minMatchCount, boolean isRequiresBonus) {
        this.index = index;
        this.winning = winning;
        this.minMatchCount = minMatchCount;
        this.isRequiresBonus = isRequiresBonus;
        this.rankCriteria = new RankCriteria(minMatchCount, isRequiresBonus);
    }
}

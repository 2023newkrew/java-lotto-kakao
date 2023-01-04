package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_NUMBERS_LENGTH;

public class RankCriteria {
    private final int minMatchCount;
    private final int maxMatchCount;
    private final boolean isRequiresBonus;

    public RankCriteria(int minMatchCount, boolean isRequiresBonus) {
        this(minMatchCount, LOTTO_NUMBERS_LENGTH, isRequiresBonus);
    }
    public RankCriteria(int minMatchCount, int maxMatchCount, boolean isRequiresBonus) {
        this.minMatchCount = minMatchCount;
        this.maxMatchCount = maxMatchCount;
        this.isRequiresBonus = isRequiresBonus;
    }

    public boolean isSatisfiedBy(LottoResult lottoResult) {
        return isMatchCountSatisfiedBy(lottoResult.getMatchCount()) && isBonusSatisfiedBy(lottoResult.hasBonus());
    }

    private boolean isMatchCountSatisfiedBy(int matchCount) {
        return minMatchCount <= matchCount && matchCount <= maxMatchCount;
    }

    private boolean isBonusSatisfiedBy(boolean hasBonus) {
        return !(isRequiresBonus && !hasBonus);
    }
}

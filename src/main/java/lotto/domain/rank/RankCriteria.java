package lotto.domain.rank;

public class RankCriteria {
    private final int minMatchCount;
    private final int maxMatchCount;
    private final IsBonusRequired isBonusRequired;

    public int getMinMatchCount() {
        return minMatchCount;
    }

    public int getMaxMatchCount() {
        return maxMatchCount;
    }

    public IsBonusRequired isBonusRequired() {
        return isBonusRequired;
    }

    public RankCriteria(int minMatchCount, int maxMatchCount, IsBonusRequired isBonusRequired) {
        this.minMatchCount = minMatchCount;
        this.maxMatchCount = maxMatchCount;
        this.isBonusRequired = isBonusRequired;
    }

    public boolean isSatisfiedBy(int matchCount, boolean hasBonus) {
        return isMatchCountSatisfiedBy(matchCount) && isBonusSatisfiedBy(hasBonus);
    }

    private boolean isMatchCountSatisfiedBy(int matchCount) {
        return minMatchCount <= matchCount && matchCount <= maxMatchCount;
    }

    private boolean isBonusSatisfiedBy(boolean hasBonus) {
        return isBonusRequired.contains(hasBonus);
    }
}

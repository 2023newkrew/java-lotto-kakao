package lotto.models;

import java.util.Objects;

public class LottoResult {
    private final Integer matchCount;

    private final boolean matchBonus;

    public LottoResult(Integer matchCount, boolean matchBonus) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult that = (LottoResult) o;
        return matchBonus == that.matchBonus && Objects.equals(matchCount, that.matchCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, matchBonus);
    }
}

package lotto.domain.rank;

import java.util.List;

public enum IsBonusRequired {
    TRUE(List.of(true)), FALSE(List.of(false)), IRRELEVANT(List.of(true, false));

    private final List<Boolean> possibleBooleans;

    IsBonusRequired(List<Boolean> possibleBooleans) {
        this.possibleBooleans = possibleBooleans;
    }

    public boolean contains(boolean bool) {
        return possibleBooleans.contains(bool);
    }
}

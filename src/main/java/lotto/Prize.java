package lotto;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Prize {
    NOTHING((overlappedNumberCount, hasBonus) -> overlappedNumberCount < 3),
    FIFTH((overlappedNumberCount, hasBonus) -> overlappedNumberCount == 3),
    FOURTH((overlappedNumberCount, hasBonus) -> overlappedNumberCount == 4),
    THIRD((overlappedNumberCount, hasBonus) -> overlappedNumberCount == 5 && !hasBonus),
    SECOND((overlappedNumberCount, hasBonus) -> overlappedNumberCount == 5 && hasBonus),
    FIRST((overlappedNumberCount, hasBonus) -> overlappedNumberCount == 6);

    private final BiPredicate<Long, Boolean> isMatched;

    Prize(BiPredicate<Long, Boolean> isMatched) {
        this.isMatched = isMatched;
    }

    public static Prize valueOf(long overlappedNumberCount, boolean hasBonus) {
        return Stream.of(Prize.values())
                .filter(p -> p.isMatched(overlappedNumberCount, hasBonus))
                .findAny()
                .orElse(Prize.NOTHING);
    }

    private boolean isMatched(long overlappedNumberCount, boolean hasBonus) {
        return isMatched.test(overlappedNumberCount, hasBonus);
    }
}

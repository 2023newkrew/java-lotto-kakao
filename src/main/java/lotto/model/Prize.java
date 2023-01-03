package lotto.model;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Prize {
    NOTHING(0L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount < 3),
    FIFTH(5_000L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount == 3),
    FOURTH(50_000L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount == 4),
    THIRD(150_000L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount == 5 && !hasBonus),
    SECOND(30_000_000L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount == 5 && hasBonus),
    FIRST(2_000_000_000L,(overlappedNumberCount, hasBonus) -> overlappedNumberCount == 6);

    private final long amount;
    private final BiPredicate<Long, Boolean> isMatched;

    Prize(long amount, BiPredicate<Long, Boolean> isMatched) {
        this.amount = amount;
        this.isMatched = isMatched;
    }

    public static Prize valueOf(long overlappedNumberCount, boolean hasBonus) {
        return Stream.of(Prize.values())
                .filter(p -> p.isMatched(overlappedNumberCount, hasBonus))
                .findAny()
                .orElse(Prize.NOTHING);
    }

    public long getAmount(){
        return amount;
    }

    private boolean isMatched(long overlappedNumberCount, boolean hasBonus) {
        return isMatched.test(overlappedNumberCount, hasBonus);
    }
}

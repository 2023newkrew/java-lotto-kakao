package lotto.model;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Prize {
    NOTHING(0L,(commonCount, hasBonus) -> false),
    FIFTH(5_000L,(commonCount, hasBonus) -> commonCount == 3),
    FOURTH(50_000L,(commonCount, hasBonus) -> commonCount == 4),
    THIRD(150_000L,(commonCount, hasBonus) -> commonCount == 5 && !hasBonus),
    SECOND(30_000_000L,(commonCount, hasBonus) -> commonCount == 5 && hasBonus),
    FIRST(2_000_000_000L,(commonCount, hasBonus) -> commonCount == 6);

    private final long amount;
    private final BiPredicate<Long, Boolean> isMatched;

    Prize(long amount, BiPredicate<Long, Boolean> isMatched) {
        this.amount = amount;
        this.isMatched = isMatched;
    }

    public static Prize from(long commonCount, boolean hasBonus) {
        return Stream.of(Prize.values())
                .filter(p -> p.isMatched(commonCount, hasBonus))
                .findAny()
                .orElse(Prize.NOTHING);
    }

    public long getAmount(){
        return amount;
    }

    private boolean isMatched(long commonCount, boolean hasBonus) {
        return isMatched.test(commonCount, hasBonus);
    }
}

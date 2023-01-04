package common.state;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.spi.ToolProvider.findFirst;

public enum Result {

    NONE((matchCount, isBonus) -> matchCount < 3, 0, ""),
    THREE((matchCount, isBonus) -> matchCount == 3, 5_000, "3개 일치"),
    FOUR((matchCount, isBonus) -> matchCount == 4, 50_000, "4개 일치"),
    FIVE((matchCount, isBonus) -> matchCount == 5 && !isBonus, 1500_000, "5개 일치"),
    FIVEBONUS((matchCount, isBonus) -> matchCount == 5 && isBonus, 30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX((matchCount, isBonus) -> matchCount == 6, 2_000_000_000, "6개 일치");

    private BiFunction<Integer, Boolean, Boolean> determine;
    private int winnings;

    private String description;

    Result(BiFunction<Integer, Boolean, Boolean> determine, int winnings, String description) {
        this.determine = determine;
        this.winnings = winnings;
        this.description = description;
    }

    public static Result createResult(int matchCount, boolean isBonus) {
        return Arrays.stream(values())
                .filter(result -> result.determine.apply(matchCount, isBonus))
                .findFirst()
                .orElse(NONE);
    }

    public int getWinnings() {
        return winnings;
    }

    public String getDescription() {
        return description;
    }
}

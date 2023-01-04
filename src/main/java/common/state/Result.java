package common.state;

import java.util.Arrays;

public enum Result {

    NONE(0, 0, ""),
    THREE(3, 5_000, "3개 일치"),
    FOUR(4, 50_000, "4개 일치"),
    FIVE(5, 1500_000, "5개 일치"),
    FIVEBONUS(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX(6, 2_000_000_000, "6개 일치");

    private int match;
    private int winnings;
    private String description;

    private Result(int match, int winnings, String description) {
        this.match = match;
        this.winnings = winnings;
        this.description = description;
    }

    public int getWinnings() {
        return winnings;
    }

    public String getDescription() {
        return description;
    }

    public static Result of(int match) {
        return Arrays.stream(values())
                .filter(v -> match == v.match)
                .findFirst()
                .orElse(NONE);
    }

}

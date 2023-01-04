package common.state;

import java.util.Arrays;

public enum Result {

    NONE(0, 0, ""),
    FIFTH_PLACE(3, 5_000, "3개 일치"),
    FOURTH_PLACE(4, 50_000, "4개 일치"),
    THIRD_PLACE(5, 1500_000, "5개 일치"),
    SECOND_PLACE(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST_PLACE(6, 2_000_000_000, "6개 일치");

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

    public static Result of(int match, boolean isBonus) {
        if (match == 5 && isBonus) {
            return Result.SECOND_PLACE;
        }
        return Arrays.stream(values())
                .filter(v -> match == v.match)
                .findFirst()
                .orElse(NONE);
    }

}

package common.state;

import view.OutputView;

import java.util.Arrays;

public enum Result {

    NONE(0, 0, OutputView.NONE_DESCRIPTION),
    FIFTH_PLACE(3, 5_000, OutputView.FIFTH_PLACE_DESCRIPTION),
    FOURTH_PLACE(4, 50_000, OutputView.FOURTH_PLACE_DESCRIPTION),
    THIRD_PLACE(5, 1500_000, OutputView.THIRD_PLACE_DESCRIPTION),
    SECOND_PLACE(5, 30_000_000, OutputView.SECOND_PLACE_DESCRIPTION),
    FIRST_PLACE(6, 2_000_000_000, OutputView.FIRST_PLACE_DESCRIPTION);

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

package common.state;

public enum Result {

    NONE(0, ""),
    THREE(5_000, "3개 일치"),
    FOUR(50_000, "4개 일치"),
    FIVE(1500_000, "5개 일치"),
    FIVEBONUS(30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX(2_000_000_000, "6개 일치");

    private int winnings;

    private String description;

    private Result(int winnings, String description) {
        this.winnings = winnings;
        this.description = description;
    }

    public int getWinnings() {
        return winnings;
    }

    public String getDescription() {
        return description;
    }
}

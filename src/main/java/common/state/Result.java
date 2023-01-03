package common.state;

public enum Result {

    NONE(0), THREE(5_000), FOUR(50_000), FIVE(1500_000), FIVEBONUS(30_000_000), SIX(2_000_000_000);

    private int winnings;

    private Result(int winnings) {
        this.winnings = winnings;
    }

}

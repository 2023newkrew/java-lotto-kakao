package domain.lotto.result;

public enum LottoResultType {

    FAIL(0, "꽝 (3개 미만) (0원)"),
    FIFTH_PLACE(5_000, "3개 일치 (5000원)"),
    FOURTH_PLACE(50_000, "4개 일치 (50000원)"),
    THIRD_PLACE(1_500_000, "5개 일치 (1500000원)"),
    SECOND_PLACE(30_000_000, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST_PLACE(2_000_000_000, "6개 일치 (2000000000원)");

    private final Integer prize;
    private final String message;

    LottoResultType(final Integer prize, final String message) {
        this.prize = prize;
        this.message = message;
    }

    public Integer getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}

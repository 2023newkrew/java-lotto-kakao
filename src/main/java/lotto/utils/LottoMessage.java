package lotto.utils;

public enum LottoMessage {
    INPUT_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WIN_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    INPUT_BONUS_BALL("보너스 볼을 입력해 주세요."),
    INPUT_MANUAL_NUMBER("수동으로 구매할 번호를 입력해 주세요."),
    INPUT_MANUAL_COUNT("수동으로 구매할 로또 수를 입력해 주세요."),
    RESULT_PURCHASE_COUNT("개를 구매했습니다."),
    RESULT_WINNING_STAT("당첨 통계"),
    RESULT_LINE("---------"),
    RESULT_THREE_MATCHING("3개 일치(5000원)_"),
    RESULT_FOUR_MATCHING("4개 일치(50000원)_"),
    RESULT_FIVE_MATCHING("5개 일치(1500000원)_"),
    RESULT_FIVE_BONUS_MATCHING("5개 일, 보너스 볼 일치(30000000원)_"),
    RESULT_SIX_MATCHING("6개 일치(2000000000원)_"),
    RESULT_BAD("(기준이 1이기 때문에 결과적으로 손해라는 의미임)"),
    RESULT_GOOD("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");

    private final String message;

    LottoMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

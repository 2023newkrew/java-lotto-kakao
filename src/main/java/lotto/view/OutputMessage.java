package lotto.view;

public enum OutputMessage {
    PRINT_READ_PRICE("구입금액을 입력해 주세요."),
    PRINT_COUNT("%d개를 구매했습니다.\n"),
    PRINT_READ_LOTTO_ANSWER("지난 주 당첨 번호를 입력해 주세요."),
    PRINT_READ_BONUS_BALL("보너스 볼을 입력해 주세요."),
    PRINT_WINNING_RESULT("\n"
            + "당첨 통계"
            + "\n---------"),
    PRINT_STATICS("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");

    private String message;

    public String getMessage() {
        return message;
    }

    OutputMessage(String message) {
        this.message = message;
    }
}

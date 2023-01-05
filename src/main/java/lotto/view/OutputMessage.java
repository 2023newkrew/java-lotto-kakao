package lotto.view;

public enum OutputMessage {
    PRINT_READ_PRICE_MESSAGE("구입금액을 입력해 주세요."),
    PRINT_COUNT_MESSAGE("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n"),

    PRINT_READ_LOTTO_ANSWER_MESSAGE("지난 주 당첨 번호를 입력해 주세요."),
    PRINT_READ_BONUS_BALL_MESSAGE("보너스 볼을 입력해 주세요."),
    PRINT_WINNING_RESULT_MESSAGE("\n"
            + "당첨 통계"
            + "\n---------\n"),
    PRINT_READ_PASSIVE_LOTTO_COUNT_MESSAGE("\n수동으로 구매할 로또 수를 입력해 주세요."),
    PRINT_READ_PASSIVE_LOTTO_NUMBERS_MESSAGE("\n수동으로 구매할 번호를 입력해 주세요.");


    private String message;

    public String getMessage() {
        return message;
    }

    OutputMessage(String message) {
        this.message = message;
    }
}

package lotto.view;

public enum InputErrorMessage {

    NULL_OR_BLANK_EXCEPTION_MESSAGE("널 또는 빈 문자열이 올 수 없습니다."),
    REQUIRED_NUMBER_EXCEPTION_MESSAGE("숫자가 주어져야 합니다."),
    MODULAR_EXCEPTION_MESSAGE("1000의 배수가 주어져야합니다."),
    BOUND_EXCEPTION_MESSAGE("1000원 이상이 주어져야합니다."),
    NOT_ENOUGH_TOTAL_AMOUNT_EXCEPTION_MESSAGE("충분한 금액이 주어져야합니다."), INVALID_LOTTO_PASSIVE_COUNT("음수 개수를 만들 수 없습니다.");

    private final String message;

    InputErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

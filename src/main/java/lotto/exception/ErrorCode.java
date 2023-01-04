package lotto.exception;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER_LENGTH("유효하지 않은 길이의 로또 번호 입니다."),
    INVALID_LOTTO_NUMBER_RANGE("유효하지 않은 범위의 로또 번호 입니다."),
    INVALID_BONUS_BALL("유효하지 않은 보너스 볼 입니다."),
    INVALID_PURCHASE_AMOUNT("구매 금액은 0원 보다 커야합니다."),
    LACK_OF_MONEY("로또를 사기 위한 돈이 부족합니다."),
    INVALID_INPUT_TYPE_NOT_INTEGER("입력 타입이 잘못되었습니다. 숫자를 입력하십시오.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
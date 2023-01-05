package lotto.exception;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER_LENGTH("유효하지 않은 길이의 로또 번호 입니다."),
    INVALID_LOTTO_NUMBER_RANGE("유효하지 않은 범위의 로또 번호 입니다."),
    INVALID_BONUS_BALL("유효하지 않은 보너스 볼 입니다."),
    INVALID_PURCHASE_AMOUNT("구매 금액은 0원 보다 커야합니다."),
    UNEXPECTED_COUNT_AND_BONUS_BALL("티켓 확인 결과와 일치하는 LOTTORANK가 없습니다."),
    EXCEED_NUMBER_OF_TICKET("구매 가능 로또 수를 초과하였습니다.");

    private final String message;

    ErrorCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
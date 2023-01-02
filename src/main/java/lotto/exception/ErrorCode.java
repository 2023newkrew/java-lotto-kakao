package lotto.exception;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER_LENGTH("유효하지 않은 길이의 로또 번호 입니다."),
    INVALID_LOTTO_NUMBER_RANGE("유효하지 않은 범위의 로또 번호 입니다."),
    INVALID_BONUS_BALL("유효하지 않은 보너스 볼 입니다.");

    private final String message;

    ErrorCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
package lotto.exception;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER("유효하지 않은 로또 번호 입니다.");

    private final String message;

    ErrorCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
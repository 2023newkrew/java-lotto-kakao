package lotto.exception;

public class LottoException extends RuntimeException{
    public LottoException(ErrorCode errorCode){
        super(errorCode.getMessage());
    }
}

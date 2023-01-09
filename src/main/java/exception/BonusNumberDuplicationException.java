package exception;

public class BonusNumberDuplicationException extends IllegalArgumentException {
    public BonusNumberDuplicationException() {
        super("보너스 번호가 당첨 번호에 이미 존재합니다.");
    }
}

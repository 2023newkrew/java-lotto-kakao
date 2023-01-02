package javalotto.exception.winninglotto;

public class BonusNumberDuplicateException extends IllegalArgumentException {
    public BonusNumberDuplicateException(int bonusNumber) {
        this(String.format("보너스 번호가 중복됩니다. 입력한 보너스 번호: %d", bonusNumber));
    }

    public BonusNumberDuplicateException(String message) {
        super(message);
    }
}

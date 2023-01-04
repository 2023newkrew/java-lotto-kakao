package domain;

import util.validator.BonusNumberValidator;
import util.validator.SingleNumberValidator;

public class LottoNumber {
    protected final int number;
    public LottoNumber(String input) {
        SingleNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }
    public LottoNumber(WinningLotto winningLotto, String input) {
        SingleNumberValidator.validate(input);
        int number = Integer.parseInt(input);
        BonusNumberValidator.validate(winningLotto, number);
        this.number = number;
    }

}

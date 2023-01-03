package domain;

import util.validator.BonusNumberValidator;

public class BonusNumber extends GeneralNumber {
    public BonusNumber(WinningLotto winningLotto, String input) {
        super(input);
        BonusNumberValidator.validate(winningLotto, this.number);
    }

}

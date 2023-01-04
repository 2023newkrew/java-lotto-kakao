package domain;

import util.validator.LottoNumberValidator;

public class LottoNumber {
    protected final int number;
    public LottoNumber(String input) {
        LottoNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }
    public LottoNumber(WinningLotto winningLotto, String input) {
        LottoNumberValidator.validate(winningLotto, input);
        this.number = Integer.parseInt(input);
    }

}

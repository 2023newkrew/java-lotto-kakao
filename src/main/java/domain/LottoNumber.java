package domain;

import util.validator.LottoNumberValidator;

import java.util.Objects;

public class LottoNumber {
    final int number;

    public LottoNumber(String input) {
        LottoNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }

    public LottoNumber(int number) {
        this.number = number;
    }

    public LottoNumber(Lotto winningLotto, String input) {
        LottoNumberValidator.validate(winningLotto, input);
        this.number = Integer.parseInt(input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}

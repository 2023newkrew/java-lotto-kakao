package domain;

import exception.DuplicateNumberException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    final int number;

    public LottoNumber(String input) {
        validate(input);
        number = Integer.parseInt(input);
    }

    public LottoNumber(int number) {
        this.number = number;
    }

    public LottoNumber(Lotto winningLotto, String input) {
        validate(winningLotto, input);
        this.number = Integer.parseInt(input);
    }

    public static void validate(String input) {
        validateNumberFormat(input);
        int number = Integer.parseInt(input);
        validateInRange(number);
    }

    public static void validate(Lotto winningLotto, String bonusInput) {
        validate(bonusInput);
        LottoNumber bonusNumber = new LottoNumber(bonusInput);
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new DuplicateNumberException("보너스 숫자가 당첨 번호 중 하나와 중복됩니다.");
        }
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 문자열이 입력되었습니다.");
        }
    }

    private static void validateInRange(int inputNumber) {
        if (!(AutoLottoGenerator.MINIMUM <= inputNumber && inputNumber <= AutoLottoGenerator.MAXIMUM)) {
            throw new IllegalArgumentException("1 이상 45 이하의 범위를 벗어났습니다.");
        }
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

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

}

package lotto;

public class LottoNumber {

    public LottoNumber(int number) {
        validateNumber(number);
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1부터 45 사이의 숫자를 입력해주세요.");
        }
    }
}

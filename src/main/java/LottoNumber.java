import java.util.Objects;

public class LottoNumber {
    private static final Integer LOTTO_NUMBER_START = 1;
    private static final Integer LOTTO_NUMBER_END = 45;

    private Integer number;

    public LottoNumber(Integer number) {
        validateLottoNumber(number);
        this.number = number;
    }

    private void validateLottoNumber(Integer number) {
        if (number < LOTTO_NUMBER_START || number > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException("로또 숫자는 1 이상 45 이하여야합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

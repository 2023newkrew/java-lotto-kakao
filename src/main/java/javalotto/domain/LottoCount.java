package javalotto.domain;

import javalotto.exception.lotto.InvalidLottoCountException;

import java.util.Objects;

public class LottoCount {
    private final int count;

    private LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount from(int count) {
        validateCount(count);

        return new LottoCount(count);
    }

    private static void validateCount(int count) {
        if (count < 0) {
            throw new InvalidLottoCountException(count);
        }
    }
    public int getCount() {
        return this.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}

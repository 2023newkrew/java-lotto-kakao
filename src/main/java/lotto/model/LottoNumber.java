package lotto.model;

import java.util.Comparator;

public final class LottoNumber implements Comparator<LottoNumber> {

    private final Integer number;

    public static final Integer MIN_RANGE = 1;

    public static final Integer MAX_RANGE = 45;

    public LottoNumber(Integer number) {
        this.number = number;
        validateNumber();
    }

    private void validateNumber() {
        if (isOnRange()) {
            throw new IllegalArgumentException(String.format("로또 숫자 범위는 %d 이상 %d 이하여야 합니다.", MIN_RANGE, MAX_RANGE));
        }
    }

    private Boolean isOnRange() {
        return number != null
                && number >= MIN_RANGE
                && number <= MAX_RANGE;
    }

    @Override
    public int compare(LottoNumber o1, LottoNumber o2) {
        return o1.number - o2.number;
    }
}

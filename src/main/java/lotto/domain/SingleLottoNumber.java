package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lotto.domain.constant.LottoRule;

public class SingleLottoNumber implements Comparable<SingleLottoNumber> {

    private final int singleLottoNumber;

    private static final Map<Integer, SingleLottoNumber> cache = new HashMap<>();

    private SingleLottoNumber(int singleLottoNumber) {
        if (singleLottoNumber < LottoRule.MIN_NUMBER || singleLottoNumber > LottoRule.MAX_NUMBER) {
            throw new IllegalArgumentException("로또의 각 숫자는 1이상 45 이하여야 합니다.");
        }

        this.singleLottoNumber = singleLottoNumber;
    }

    public int getSingleLottoNumber() {
        return singleLottoNumber;
    }

    public static SingleLottoNumber from(int singleLottoNumber) {
        if (!cache.containsKey(singleLottoNumber)) {
            cache.put(singleLottoNumber, new SingleLottoNumber(singleLottoNumber));
        }
        return cache.get(singleLottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingleLottoNumber that = (SingleLottoNumber) o;
        return singleLottoNumber == that.singleLottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(singleLottoNumber);
    }

    @Override
    public int compareTo(SingleLottoNumber o) {
        return this.singleLottoNumber - o.getSingleLottoNumber();
    }
}

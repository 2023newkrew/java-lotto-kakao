package lotto;

import lotto.exception.InvalidLottoCountValue;

import java.util.Objects;

public class LottoCount {

    private final int count;
    public LottoCount(int number) {
        if (number<0){
            throw new InvalidLottoCountValue();
        }
        this.count = number;
    }
    public LottoCount(Cash cash){
        this((int)(cash.getCash() / LottoConstants.LOTTO_PRICE));
    }

    public int getCount() {
        return count;
    }
    public LottoCount add(int val){
        return new LottoCount(count+val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return count+"개";
    }
}

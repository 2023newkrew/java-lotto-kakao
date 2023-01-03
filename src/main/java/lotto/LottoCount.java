package lotto;

import lotto.exception.InvalidLottoNumberValue;

import java.util.Objects;

public class LottoCount {
    public int getCount() {
        return count;
    }

    private int count;
    public LottoCount(int number) {
        if (number<=0){
            throw new InvalidLottoNumberValue();
        }
        this.count = number;
    }
    public LottoCount(Cash cash){
        this.count = (int)(cash.getCash() / LottoConstants.LOTTO_PRICE);
        if (this.count <= 0){
            throw new InvalidLottoNumberValue();
        }
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

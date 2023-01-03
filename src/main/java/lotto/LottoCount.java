package lotto;

import lotto.exception.InvalidLottoNumberValue;

import java.util.Objects;

public class LottoCount {
    private int number;
    public LottoCount(int number) {
        if (number<0){
            throw new InvalidLottoNumberValue();
        }
        this.number = number;
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number+"개";
    }
}

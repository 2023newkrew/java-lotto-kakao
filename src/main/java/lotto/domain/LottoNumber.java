package lotto.domain;

import static lotto.utils.Constants.*;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number){
        rangeCheck(number);
        this.number = number;
    }

    public Integer getNumber(){
        return this.number;
    }

    private void rangeCheck(int number){
        if(number < LOTTO_LOWER_BOUND || number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호가 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }
}
package lotto.domain;

import static lotto.utils.Constants.*;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number){
        this.number = number;
        rangeCheck();
    }

    public Integer getNumber(){
        return this.number;
    }

    public void rangeCheck(){
        if(this.number < LOTTO_LOWER_BOUND || this.number > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호가 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }
}
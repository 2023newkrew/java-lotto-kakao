package lotto.domain;


import java.util.List;

import static lotto.domain.LottoNumber.LOTTO_LOWER_BOUND;
import static lotto.domain.LottoNumber.LOTTO_UPPER_BOUND;

public class LottoWinnerTicket {
    private final LottoNumber winNumber;
    private final int bonusBall;


    public LottoWinnerTicket(LottoNumber winNumber, int bonusBall) {
        this.winNumber = winNumber;
        this.bonusBall = bonusBall;
        bonusRangeCheck();
    }


    public List<Integer> getLottoNumbers() {
        return winNumber.getLottoNumbers();
    }

    public Integer getBonusNumber() {
        return bonusBall;
    }

    private void bonusRangeCheck(){
        if(this.bonusBall < LOTTO_LOWER_BOUND || this.bonusBall > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호가 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }
}

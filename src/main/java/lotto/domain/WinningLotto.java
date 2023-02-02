package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numList, Integer bonusNumber) {
        super(numList);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}

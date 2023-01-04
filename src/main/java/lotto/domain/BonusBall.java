package lotto.domain;

import static lotto.domain.LottoNumber.from;

public class BonusBall {

    private final LottoNumber bonusBall;

    public static BonusBall createBonusBall(int bonusBall){
        return new BonusBall(bonusBall);
    }

    private BonusBall(int bonusBall) {
        this.bonusBall = from(bonusBall);
    }

    public boolean hasBonusBall(LottoNumbers lottoNumbers){
        return lottoNumbers.contains(bonusBall);
    }




}

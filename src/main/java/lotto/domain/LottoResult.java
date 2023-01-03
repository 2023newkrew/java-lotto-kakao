package lotto.domain;

public class LottoResult {
    private final Lotto myLotto;
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public LottoResult(Lotto myLotto, Lotto winningLotto, LottoNumber bonusNumber) {
        this.myLotto = myLotto;
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank getRank() {
        if( LottoRank.FIRST.COUNT == myLotto.compare(winningLotto))
            return LottoRank.FIRST;
        if(LottoRank.SECOND.COUNT == myLotto.compare(winningLotto)
            && myLotto.hasBonus(bonusNumber))
            return LottoRank.SECOND;
        if(LottoRank.THIRD.COUNT == myLotto.compare(winningLotto))
            return LottoRank.THIRD;
        if(LottoRank.FOURTH.COUNT == myLotto.compare(winningLotto))
            return LottoRank.FOURTH;
        if(LottoRank.FIFTH.COUNT == myLotto.compare(winningLotto))
            return LottoRank.FIFTH;

        return LottoRank.FIRST;
    }
}

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
        int matchCount = myLotto.getMatchCount(winningLotto);
        if (matchCount == LottoRank.FIRST.COUNT) {
            return LottoRank.FIRST;
        } else if (matchCount == LottoRank.SECOND.COUNT && myLotto.hasBonus(bonusNumber)) {
            return LottoRank.SECOND;
        } else if (matchCount == LottoRank.THIRD.COUNT) {
            return LottoRank.THIRD;
        } else if (matchCount == LottoRank.FOURTH.COUNT) {
            return LottoRank.FOURTH;
        } else if (matchCount == LottoRank.FIFTH.COUNT) {
            return LottoRank.FIFTH;
        }
        return LottoRank.FAIL;
    }
}

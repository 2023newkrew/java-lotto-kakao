package lotto.domain;

public class LottoResult {

    private final Integer matchCount;
    private final Boolean matchedBonus;

    public LottoResult(Lotto myLotto, WinningLotto winningLotto) {
        this.matchCount = myLotto.getMatchCount(winningLotto);
        this.matchedBonus = myLotto.hasBonus(winningLotto.getBonusNumber());
    }

    public LottoRank getRank() {
        return LottoRank.getRank(matchCount, matchedBonus);
    }
}

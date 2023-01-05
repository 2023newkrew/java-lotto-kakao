package lotto.domain;

public class LottoResult {
    private final int count;
    private final boolean bonus;

    public LottoResult(Lotto myLotto, Lotto winningLotto, LottoNumber bonusNumber) {
        this.count = myLotto.compare(winningLotto);
        this.bonus = myLotto.hasBonus(bonusNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof LottoResult))
            return false;
        return count == ((LottoResult) obj).count
                && bonus == ((LottoResult) obj).bonus;
    }

    public LottoRank getRank() {
        return LottoRank.getRank(count, bonus);
    }
}

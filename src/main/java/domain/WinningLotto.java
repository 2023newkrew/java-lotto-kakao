package domain;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public boolean isBonus(Lotto lotto) {
        return lotto.containsNumber(bonusNumber);
    }

    public Lotto getWinningLotto(){
        return winningLotto;
    }

}

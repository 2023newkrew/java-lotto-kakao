package domain;

public class WinningLottoWithBonus {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLottoWithBonus(Lotto winningLotto, LottoNumber bonusNumber) {
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

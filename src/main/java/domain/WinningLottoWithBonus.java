package domain;

public class WinningLottoWithBonus {
    private final Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningLottoWithBonus(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningLotto(){
        return winningLotto;
    }

}

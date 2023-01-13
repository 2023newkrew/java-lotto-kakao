package domain;

public class WinningLotto {

    public static final String NUMBER_INCLUDED_IN_WINNING_LOTTO_NUMBERS_CANNOT_BE_BONUS_NUMBER_MSG = "당첨 번호에 포함된 번호는 보너스 번호가 될 수 없습니다.";
    private final LottoNumbers winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers winningLottoNumbers, LottoNumber bonusNumber) {
        if(winningLottoNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException(NUMBER_INCLUDED_IN_WINNING_LOTTO_NUMBERS_CANNOT_BE_BONUS_NUMBER_MSG);
        }

        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getWinningLottoNumbers() {
        return winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}

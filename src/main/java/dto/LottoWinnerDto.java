/**
 * controller에서 받은 당첨 로또 번호를
 * model로 보내준다
 */
package dto;

import model.LottoNumber;
import model.LottoWinner;
import model.Lotto;

public class LottoWinnerDto {
    private final Lotto winNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinnerDto(Lotto winNumbers, LottoNumber bonusNumber){
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoWinner getLottoWinner() {
        return new LottoWinner(winNumbers, bonusNumber);
    }
}

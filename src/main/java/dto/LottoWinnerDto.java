/**
 * controller에서 받은 당첨 로또 번호를
 * model로 보내준다
 */
package dto;

import model.LottoNumber;
import model.LottoWinner;
import model.Lotto;

public class LottoWinnerDto {
    private final LottoWinner lottoWinner;

    public LottoWinnerDto(Lotto winNumbers, LottoNumber bonusNumber) {
        lottoWinner = new LottoWinner(winNumbers, bonusNumber);
    }

    public LottoWinner getLottoWinner() {
        return lottoWinner;
    }
}

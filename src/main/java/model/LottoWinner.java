/**
 * 당첨 번호(로또, 보너스 번호)를 저장하는 객체이다
 * 사용자의 로또 번호를 받으면 몇 등인지 알려준다
 * 당첨 결과가 어딘가에서 바뀌어선 안된다
 */

package model;

import exception.LottoNumberDuplicateException;
import model.constant.LottoPlace;

public class LottoWinner {

    private final Lotto winNumbers;
    private final LottoNumber bonusNumber;

    /**
     * @throws LottoNumberDuplicateException - 입력된 보너스 번호가 당첨 로또들 중 하나와 같을 때 발생하는 예외
     */
    public LottoWinner(Lotto winNumbers, LottoNumber bonusNumber) throws LottoNumberDuplicateException {
        if (winNumbers.getLottoNumbers().contains(bonusNumber)) {
            throw new LottoNumberDuplicateException();
        }
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoPlace getLottoPlace(Lotto lottoNumbers) {
        int matchCount = getMatchCount(lottoNumbers);
        boolean isBonusMatch = isMatchBonusNumber(lottoNumbers);
        if (matchCount == 6) return LottoPlace.FIRST_PLACE;
        if (matchCount == 5 && isBonusMatch) return LottoPlace.SECOND_PLACE;
        if (matchCount == 5) return LottoPlace.THIRD_PLACE;
        if (matchCount == 4) return LottoPlace.FOURTH_PLACE;
        if (matchCount == 3) return LottoPlace.FIFTH_PLACE;
        return LottoPlace.LOSE;
    }

    private int getMatchCount(final Lotto lottoNumbers) {
        return Math.toIntExact(winNumbers.getLottoNumbers().stream()
                .filter(winNumber -> lottoNumbers.getLottoNumbers().contains(winNumber))
                .count());
    }

    private boolean isMatchBonusNumber(Lotto lottoNumbers) {
        return lottoNumbers.getLottoNumbers().contains(bonusNumber);
    }

}

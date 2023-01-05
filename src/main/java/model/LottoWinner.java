/**
 * 당첨 번호(로또, 보너스 번호)를 저장하는 객체이다
 * 사용자의 로또 번호를 받으면 몇 등인지 알려준다
 * 당첨 결과가 어딘가에서 바뀌어선 안된다
 */

package model;

import exception.LottoNumberException;
import model.constant.LottoPlace;

public class LottoWinner {

    private final Lotto winNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinner(Lotto winNumbers, LottoNumber bonusNumber) {
        if(winNumbers.getLottoNumbers().contains(bonusNumber)) {
            throw new LottoNumberException("보너스 번호는 로또 번호들과 달라야 합니다.");
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

/**
 * 당첨 로또 번호를 저장하는 객체
 * 사용자의 로또 번호를 받으면 몇 등인지 알려준다
 */

package model;

import model.constant.LottoPlace;


public class LottoWinner {

    private final LottoLine winNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinner(LottoLine winNumbers, LottoNumber bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoPlace getLottoPlace(LottoLine lottoNumbers) {
        int matchCount = getMatchCount(lottoNumbers);
        boolean isBonusMatch = isMatchBonusNumber(lottoNumbers);
        if (matchCount == 6) return LottoPlace.FIRST_PLACE;
        if (matchCount == 5 && isBonusMatch) return LottoPlace.SECOND_PLACE;
        if (matchCount == 5) return LottoPlace.THIRD_PLACE;
        if (matchCount == 4) return LottoPlace.FOURTH_PLACE;
        if (matchCount == 3) return LottoPlace.FIFTH_PLACE;
        return LottoPlace.LOSE;
    }

    private int getMatchCount(final LottoLine lottoNumbers) {
        return Math.toIntExact(winNumbers.getLottoNumbers().stream()
                .filter(winNumber -> lottoNumbers.getLottoNumbers().contains(winNumber))
                .count());
    }

    private boolean isMatchBonusNumber(LottoLine lottoNumbers) {
        return lottoNumbers.getLottoNumbers().contains(bonusNumber);
    }

}

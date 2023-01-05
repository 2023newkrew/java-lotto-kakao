/**
 * 은행: 로또를 가져오면 등수에 맞는 돈을 준다
 * 등수 -> 돈을 연결해주는 역할을 한다
 */
package model;

import model.constant.LottoPlace;
import model.constant.LottoPrizeMoney;

public class Banker {
    public long getTotalPrizeMoney(LottoResult lottoResult) { //전체 상금 리턴
        long answer = 0;
        for (LottoPlace place : lottoResult.getLottoPlaces().keySet()) {
            answer += getPrizeMoney(place) * lottoResult.getLottoPlaces().get(place);
        }
        return answer;
    }

    private long getPrizeMoney(LottoPlace lottoPlace) { //등수 당 상금 매핑
        if (lottoPlace == LottoPlace.FIRST_PLACE) return LottoPrizeMoney.FIRST_PRIZE_MONEY.valueOf();
        if (lottoPlace == LottoPlace.SECOND_PLACE) return LottoPrizeMoney.SECOND_PRIZE_MONEY.valueOf();
        if (lottoPlace == LottoPlace.THIRD_PLACE) return LottoPrizeMoney.THIRD_PRIZE_MONEY.valueOf();
        if (lottoPlace == LottoPlace.FOURTH_PLACE) return LottoPrizeMoney.FOURTH_PRIZE_MONEY.valueOf();
        if (lottoPlace == LottoPlace.FIFTH_PLACE) return LottoPrizeMoney.FIFTH_PRIZE_MONEY.valueOf();
        return LottoPrizeMoney.LOSE_PRIZE_MONEY.valueOf();
    }
}

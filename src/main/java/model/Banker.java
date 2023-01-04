/**
 * 은행: 로또를 가져오면 등수에 맞는 돈을 준다
 * 등수 -> 돈을 연결해주는 역할을 한다
 */
package model;

import model.constant.LottoPlace;
import model.constant.LottoPrizeMoney;

import java.util.List;

public class Banker {
    public long getTotalPrizeMoney(List<LottoPlace> lottoResult) { //사용자가 조작할 수 있어선 안됨..
        long answer = 0;
        for(LottoPlace place : lottoResult) {
            answer += getPrizeMoney(place).valueOf();
        }
        return answer;
    }
    private LottoPrizeMoney getPrizeMoney(LottoPlace lottoPlace) {
        if (lottoPlace == LottoPlace.FIRST_PLACE) return LottoPrizeMoney.FIRST_PRIZE_MONEY;
        if (lottoPlace == LottoPlace.SECOND_PLACE) return LottoPrizeMoney.SECOND_PRIZE_MONEY;
        if (lottoPlace == LottoPlace.THIRD_PLACE) return LottoPrizeMoney.THIRD_PRIZE_MONEY;
        if (lottoPlace == LottoPlace.FOURTH_PLACE) return LottoPrizeMoney.FOURTH_PRIZE_MONEY;
        if (lottoPlace == LottoPlace.FIFTH_PLACE) return LottoPrizeMoney.FIFTH_PRIZE_MONEY;
        return LottoPrizeMoney.LOSE_PRIZE_MONEY;
    }
}

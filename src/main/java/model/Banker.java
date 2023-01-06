/**
 * 은행: 로또를 가져오면 등수에 맞는 돈을 준다
 * 등수 -> 돈을 연결해주는 역할을 한다
 */
package model;

import model.constant.LottoPlace;

public class Banker {
    public long getTotalPrizeMoney(LottoResult lottoResult) { //전체 상금 리턴
        long answer = 0;
        for (LottoPlace place : lottoResult.getLottoPlaces().keySet()) {
            answer += getPrizeMoney(place) * lottoResult.getLottoPlaces().get(place);
        }
        return answer;
    }

    private long getPrizeMoney(LottoPlace lottoPlace) { //등수 당 상금 매핑
        return lottoPlace.valueOf();
    }
}

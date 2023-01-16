/**
 * 은행: 로또를 가져오면 등수에 맞는 돈을 준다
 * 등수 -> 돈을 연결해주는 역할을 한다
 */
package model;

import model.constant.LottoPlace;

public class Banker {
    public Money getTotalPrizeMoney(LottoResult lottoResult) { //전체 상금 리턴
        Money money = new Money();
        for (LottoPlace place : lottoResult.getLottoPlaces().keySet()) {
            money.add(getPrizeMoney(place), lottoResult.getLottoPlaces().get(place));

        }
        return money;
    }

    private Money getPrizeMoney(LottoPlace lottoPlace) { //등수 당 상금 매핑
        return new Money(lottoPlace.valueOf());
    }
}

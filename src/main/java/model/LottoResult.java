/**
 * 로또의 결과를 가지고 있다
 */
package model;

import model.constant.LottoPlace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final List<LottoPlace> lottoResult = new ArrayList<>();

    public LottoResult(LottoWinner lottoWinner, LottoTicket lottoTicket) {
        for (Lotto lotto : lottoTicket.getLottoList()) {
            lottoResult.add(lottoWinner.getLottoPlace(lotto));
        }
    }

    public Map<LottoPlace, Integer> getLottoPlaces() { // 등수 당 몇명인지 리턴
        Map<LottoPlace, Integer> places = new HashMap<>();

        for (LottoPlace place : lottoResult) {
            places.put(place, places.getOrDefault(place, 0) + 1);
        }

        return places;
    }
}

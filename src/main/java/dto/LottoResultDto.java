/**
 * main에서 생성된 결과를
 * view로 보내준다
 */
package dto;

import model.constant.LottoPlace;

import java.util.Map;

public class LottoResultDto {
    private final Map<LottoPlace, Integer> placeResult;
    private final double rateResult;

    public LottoResultDto(Map<LottoPlace,Integer> placeResult, double rateResult) {
        this.placeResult = placeResult;
        this.rateResult = rateResult;
    }

    public Map<LottoPlace, Integer> getPlaceResult() {
        return placeResult;
    }

    public double getRateResult() {
        return rateResult;
    }

}

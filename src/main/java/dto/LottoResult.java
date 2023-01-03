package dto;

import domain.LottoPlace;

import java.util.Map;

public class LottoResult {
    private final Map<LottoPlace, Integer> lottoPlaces;
    private final Double earningRate;

    public LottoResult(Map<LottoPlace, Integer> lottosStatus, Double earningRate) {
        this.lottoPlaces = lottosStatus;
        this.earningRate = earningRate;
    }

    public Map<LottoPlace, Integer> getLottoPlaces() {
        return lottoPlaces;
    }

    public Double getEarningRate() {
        return earningRate;
    }
}

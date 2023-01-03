package dto;

import domain.LottoRank;

import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoPlaces;
    private final Double earningRate;

    public LottoResult(Map<LottoRank, Integer> lottosStatus, Double earningRate) {
        this.lottoPlaces = lottosStatus;
        this.earningRate = earningRate;
    }

    public Map<LottoRank, Integer> getLottoPlaces() {
        return lottoPlaces;
    }

    public Double getEarningRate() {
        return earningRate;
    }
}

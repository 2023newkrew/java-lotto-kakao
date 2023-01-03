package dto;

import domain.LottoRank;

import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoRanks;
    private final Double earningRate;

    public LottoResult(Map<LottoRank, Integer> lottoRanks, Double earningRate) {
        this.lottoRanks = lottoRanks;
        this.earningRate = earningRate;
    }

    public Map<LottoRank, Integer> getLottoRanks() {
        return lottoRanks;
    }

    public Double getEarningRate() {
        return earningRate;
    }
}

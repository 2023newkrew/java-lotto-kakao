package dto;

import domain.LottoRank;

import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoRanks;
    private final Double earningRate;

    public LottoResult(Map<LottoRank, Integer> lottosStatus, Double earningRate) {
        this.lottoRanks = lottosStatus;
        this.earningRate = earningRate;
    }

    public Map<LottoRank, Integer> getLottoRanks() {
        return lottoRanks;
    }

    public Double getEarningRate() {
        return earningRate;
    }
}

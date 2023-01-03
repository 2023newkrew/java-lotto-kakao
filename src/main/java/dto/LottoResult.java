package dto;

import domain.Rank;

import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> lottoRanks;
    private final Double earningRate;

    public LottoResult(Map<Rank, Integer> lottosStatus, Double earningRate) {
        this.lottoRanks = lottosStatus;
        this.earningRate = earningRate;
    }

    public Map<Rank, Integer> getLottoRanks() {
        return lottoRanks;
    }

    public Double getEarningRate() {
        return earningRate;
    }
}

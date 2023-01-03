package lotto.dto;

import java.util.List;

public class GameResultDto {
    private final List<LottoResult> lottoResults;
    private final float rate;

    public GameResultDto(List<LottoResult> lottoResults, float rate) {
        this.lottoResults = lottoResults;
        this.rate = rate;
    }

    public List<LottoResult> getLottoResults() {
        return lottoResults;
    }

    public float getRate() {
        return rate;
    }
}

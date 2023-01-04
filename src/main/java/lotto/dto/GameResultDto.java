package lotto.dto;

import lotto.constant.LottoGrade;

import java.util.HashMap;
import java.util.Map;

public class GameResultDto {
    private final Map<LottoGrade, Integer> lottoResultCounter;
    private final float rate;

    public GameResultDto(Map<LottoGrade, Integer> lottoResultCounter, float rate) {
        this.lottoResultCounter = lottoResultCounter;
        this.rate = rate;
    }

    public Map<LottoGrade, Integer> getLottoResultCounter() {
        return new HashMap<>(lottoResultCounter);
    }

    public float getEarningRate() {
        return rate;
    }
}

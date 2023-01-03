package lotto.dto;

import lotto.constant.LottoGradeEnum;

import java.util.HashMap;
import java.util.Map;

public class GameResultDto {
    private final Map<LottoGradeEnum, Integer> lottoResultCounter;
    private final float rate;

    public GameResultDto(Map<LottoGradeEnum, Integer> lottoResultCounter, float rate) {
        this.lottoResultCounter = lottoResultCounter;
        this.rate = rate;
    }

    public Map<LottoGradeEnum, Integer> getLottoResultCounter() {
        return new HashMap<>(lottoResultCounter);
    }

    public float getRate() {
        return rate;
    }
}

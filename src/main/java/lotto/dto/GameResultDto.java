package lotto.dto;

import java.util.List;

public class GameResultDto {
    private final List<LottoGradeCountResult> lottoGradeCountResults;
    private final float rateOfReturn;

    public GameResultDto(List<LottoGradeCountResult> lottoGradeCountResults, float rateOfReturn) {
        this.lottoGradeCountResults = lottoGradeCountResults;
        this.rateOfReturn = rateOfReturn;
    }

    public List<LottoGradeCountResult> getLottoResults() {
        return lottoGradeCountResults;
    }

    public float getRateOfReturn() {
        return rateOfReturn;
    }
}

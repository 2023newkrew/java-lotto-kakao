package lotto.dto;

import lotto.LottoGradeEnum;

public class LottoGradeCountResult {

    private final LottoGradeEnum lottoGrade;
    private final int gradeCount;

    public LottoGradeCountResult(LottoGradeEnum lottoGrade, int gradeCount) {
        this.lottoGrade = lottoGrade;
        this.gradeCount = gradeCount;
    }

    public LottoGradeEnum getGrade() {
        return lottoGrade;
    }

    @Override
    public String toString() {
        return String.format(
                "%d개 일치%s(%d원)- %d개",
                lottoGrade.getMatchCount(),
                lottoGrade == LottoGradeEnum.SECOND ? ", 보너스 볼 일치" : " ",
                lottoGrade.getPrice(),
                gradeCount
        );
    }
}

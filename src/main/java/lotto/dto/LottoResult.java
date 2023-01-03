package lotto.dto;

import lotto.constant.LottoGradeEnum;

public class LottoResult implements Comparable<LottoResult> {
    private final LottoGradeEnum lottoGrade;
    private final int gradeCount;

    public LottoResult(LottoGradeEnum lottoGrade, int gradeCount) {
        this.lottoGrade = lottoGrade;
        this.gradeCount = gradeCount;
    }

    public LottoGradeEnum getGrade() {
        return lottoGrade;
    }

    public int getGradeCount() {
        return gradeCount;
    }


    @Override
    public int compareTo(LottoResult o) {
        return Integer.compare(this.lottoGrade.price, o.lottoGrade.price);
    }
}

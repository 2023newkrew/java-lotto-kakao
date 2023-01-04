package lotto.domain;

public class LottoIncomeRate {
    private final float incomeRate;

    public LottoIncomeRate(LottoStatistics lottoStatistics, int lottoTicketPrice) {
        this(getLottoIncomeRate(lottoStatistics, lottoTicketPrice));
    }

    public LottoIncomeRate(float incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getString() {
        return String.format("총 수익률은 %.2f입니다.", incomeRate);
    }

    private static float getLottoIncomeRate(LottoStatistics lottoStatistics, int lottoTicketPrice) {
        return lottoStatistics.getIncome()
                / (float) (lottoTicketPrice * lottoStatistics.getTotal());
    }
}

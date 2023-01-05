package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.result.LottoIncomeRate;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoIncomeRateTest {
    @DisplayName("당첨 총 수익률 계산")
    @Test
    public void getLottoNumbersStatistics() {
        List<LottoResult> lottoResultList = new ArrayList<>(
                List.of(LottoResult.FIRST, LottoResult.FOURTH, LottoResult.FIFTH));
        LottoStatistics lottoStatistics = new LottoStatistics(lottoResultList);
        LottoIncomeRate lottoIncomeRate = new LottoIncomeRate(lottoStatistics, 1000);
        Assertions.assertThat(lottoIncomeRate.getString())
                .isEqualTo("총 수익률은 666685.00입니다.");
    }
}

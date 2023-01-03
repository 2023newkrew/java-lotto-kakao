package lotto;

import lotto.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @DisplayName("당첨 결과를 집계한다.")
    @Test
    void judgeLotto() {
        Lotto firstPrizeLotto = Lotto.from(TestUtil.toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Lotto thirdPrizeLotto = Lotto.from(TestUtil.toLottoNumbers(List.of(1, 2, 3, 4, 5, 8)));
        Lotto fifthPrizeLotto = Lotto.from(TestUtil.toLottoNumbers(List.of(1, 2, 3, 7, 8, 9)));
        List<Lotto> lottos = List.of(firstPrizeLotto,
                                     thirdPrizeLotto, thirdPrizeLotto,
                                     fifthPrizeLotto, fifthPrizeLotto, fifthPrizeLotto);
        WinningNumbers winningNumbers = new WinningNumbers(firstPrizeLotto, LottoNumber.valueOf(7));
        LottoCompany lottoCompany = new LottoCompany(winningNumbers);
        Money money = Money.valueOf(6000);

        WinningStatistics actual = lottoCompany.judge(LottoBundle.from(lottos), money);

        assertThat(actual.countBy(Prize.FIRST)).isEqualTo(1L);
        assertThat(actual.countBy(Prize.SECOND)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.THIRD)).isEqualTo(2L);
        assertThat(actual.countBy(Prize.FOURTH)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.FIFTH)).isEqualTo(3L);
        assertThat(actual.countBy(Prize.NOTHING)).isEqualTo(0L);
    }
}
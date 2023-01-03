package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @DisplayName("당첨 결과를 집계한다.")
    @Test
    void judgeLotto() {
        Lotto firstPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 6);
        Lotto thirdPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 8);
        Lotto fifthPrizeLotto = Lotto.create(1, 2, 3, 7, 8, 9);
        LottoGenerator lottoGenerator = count ->
                List.of(firstPrizeLotto,
                        thirdPrizeLotto, thirdPrizeLotto,
                        fifthPrizeLotto, fifthPrizeLotto, fifthPrizeLotto);
        WinningNumbers winningNumbers = new WinningNumbers(firstPrizeLotto, new LottoNumber(7));
        LottoCompany lottoCompany = new LottoCompany(winningNumbers, lottoGenerator);

        WinningStatistics actual = lottoCompany.play(6000);

        assertThat(actual.countBy(Prize.FIRST)).isEqualTo(1L);
        assertThat(actual.countBy(Prize.SECOND)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.THIRD)).isEqualTo(2L);
        assertThat(actual.countBy(Prize.FOURTH)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.FIFTH)).isEqualTo(3L);
        assertThat(actual.countBy(Prize.NOTHING)).isEqualTo(0L);
    }

    @DisplayName("money가 0원 이하일 때 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(longs = {0L, -1L, -100L})
    void judgeWithNullLottoNumbers(long money) {
        LottoGenerator lottoGenerator = count -> Collections.emptyList();
        WinningNumbers winningNumbers = new WinningNumbers(Lotto.create(1, 2, 3, 4, 5, 6), new LottoNumber(7));
        LottoCompany lottoCompany = new LottoCompany(winningNumbers, lottoGenerator);

        Assertions.assertThatThrownBy(() -> lottoCompany.play(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 0원을 초과해야 합니다.");
    }
}
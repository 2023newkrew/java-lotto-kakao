package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class LottoCompanyTest {

    @DisplayName("당첨 결과를 집계한다.")
    @Test
    void judgeLotto() {
        Lotto firstPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 6);
        Lotto thirdPrizeLotto = Lotto.create(1, 2, 3, 4, 5, 8);
        Lotto fifthPrizeLotto = Lotto.create(1, 2, 3, 7, 8, 9);
        WinningNumbers winningNumbers = new WinningNumbers(firstPrizeLotto, new LottoNumber(7));

        LottoCompany lottoCompany = new LottoCompany(winningNumbers);

        Prizes actual = lottoCompany.judge(
                List.of(firstPrizeLotto,
                        thirdPrizeLotto, thirdPrizeLotto,
                        fifthPrizeLotto, fifthPrizeLotto, fifthPrizeLotto)
        );

        assertThat(actual.countBy(Prize.FIRST)).isEqualTo(1L);
        assertThat(actual.countBy(Prize.SECOND)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.THIRD)).isEqualTo(2L);
        assertThat(actual.countBy(Prize.FOURTH)).isEqualTo(0L);
        assertThat(actual.countBy(Prize.FIFTH)).isEqualTo(3L);
        assertThat(actual.countBy(Prize.NOTHING)).isEqualTo(0L);
    }

    @DisplayName("lottos가 null일 경우에는 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void judgeWithNullLottoNumbers(List<Lotto> lottos) {
        WinningNumbers winningNumbers = new WinningNumbers(Lotto.create(1, 2, 3, 4, 5, 6), new LottoNumber(7));
        LottoCompany lottoCompany = new LottoCompany(winningNumbers);

        Assertions.assertThatThrownBy(() -> lottoCompany.judge(lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매한 로또가 없습니다.");
    }
}

package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;
import lotto.service.LottoCalculator;
import lotto.utils.StringConversion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 계산해야 한다.")
    void lottoCorrectNumberTest() {
        // 당첨 번호
        LottoWinnerTicket winTicket = new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))), 7);

        // 사용자가 뽑은 로또 번호들
        LottoTicket userTicket1 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 9, 10, 11)));
        LottoTicket userTicket2 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 9, 11)));
        LottoTicket userTicket3 = new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 11)));

        LottoCalculator lottoCalculator = new LottoCalculator(winTicket);

        assertThat(lottoCalculator.checkSameCount(userTicket1)).isEqualTo(3);
        assertThat(lottoCalculator.checkSameCount(userTicket2)).isEqualTo(4);
        assertThat(lottoCalculator.checkSameCount(userTicket3)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 볼과 일치하는 볼이 있는지 확인할 수 있어야 한다.")
    void lottoBonusCheckTest() {
        LottoWinnerTicket winTicket = new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))), 22);
        LottoCalculator lottoCalculator = new LottoCalculator(winTicket);

        LottoTicket userTicket1 = new LottoTicket(new ArrayList<>(List.of(1, 3, 4, 5, 6, 22)));
        Assertions.assertThat(lottoCalculator.isBonusNumber(userTicket1)).isTrue();

        LottoTicket userTicket2 = new LottoTicket(new ArrayList<>(List.of(1, 3, 4, 5, 6, 23)));
        Assertions.assertThat(lottoCalculator.isBonusNumber(userTicket2)).isFalse();
    }


    @ParameterizedTest
    @ValueSource(strings = {"0,0,0,5,0"})
    @DisplayName("당첨금액의 합을 알 수 있어야 한다.")
    void lottoWinningAmountTest(String userNumber) {
        StringConversion stringConversion = new StringConversion();
        LottoCalculator lottoCalculator = new LottoCalculator(new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))), 7));

        ArrayList<Integer> winScore = new ArrayList<>(
                List.of(stringConversion.convertToArray(userNumber)));
        long summary = lottoCalculator.getWinSummary(winScore);
        assertThat(summary).isEqualTo(10000000000L);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 10500, 10900})
    @DisplayName("수익률을 계산해야 한다.")
    void lottoRateOfReturnTest(int amount) {
        LottoCalculator lottoCalculator = new LottoCalculator(new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))), 7));

        lottoCalculator.getScore(new LottoTicket(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7))));
        assertThat(lottoCalculator.calcRateOfReturn(amount)).isEqualTo(3000);
    }
}
package domain;

import dto.LottoResult;
import dto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoBuyerTest {

    @Test
    void 로또_당첨_등수를_확인한다() {
        // given
        int price = 3500;
        List<Lotto> lottos = List.of(
                Lotto.ofNumbers(List.of(1,2,3,4,5,6)),
                Lotto.ofNumbers(List.of(3,4,5,6,7,8)),
                Lotto.ofNumbers(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(price, (money)->lottos);

        Lotto winningNumbers = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);

        // then
        assertThat(lottoResult.getLottoRanks().get(LottoRank.FIRST_RANK)).isEqualTo(1);
        assertThat(lottoResult.getLottoRanks().get(LottoRank.SECOND_RANK)).isEqualTo(0);
        assertThat(lottoResult.getLottoRanks().get(LottoRank.THIRD_RANK)).isEqualTo(0);
        assertThat(lottoResult.getLottoRanks().get(LottoRank.FOURTH_RANK)).isEqualTo(1);
        assertThat(lottoResult.getLottoRanks().get(LottoRank.FIFTH_RANK)).isEqualTo(0);
    }

    @Test
    void 로또_수익률을_계산한다() {
        // given
        int price = 3500;
        List<Lotto> lottos = List.of(
                Lotto.ofNumbers(List.of(1,2,3,4,5,6)),
                Lotto.ofNumbers(List.of(3,4,5,6,7,8)),
                Lotto.ofNumbers(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(price, (money)->lottos);

        Lotto winningNumbers = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);

        // when, then
        assertThat(lottoBuyer.calculateEarningRate(lottoResult.getLottoRanks())).isEqualTo(666683.33);
    }
}

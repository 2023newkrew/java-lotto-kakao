package domain;

import dto.LottoResult;
import dto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoBuyerTest {

    @Test
    void 로또_당첨_등수를_확인한다() {
        int price = 3500;
        List<Lotto> lottos = List.of(
                Lotto.ofNumbers(List.of(1,2,3,4,5,6)),
                Lotto.ofNumbers(List.of(3,4,5,6,7,8)),
                Lotto.ofNumbers(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(price);
        lottoBuyer.buyAutomaticLottos(price / LottoStore.COST);

        Lotto winningNumbers = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);
        assertThat(lottoResult.getLottoRanks().get(Rank.FIRST_RANK)).isEqualTo(1);
        assertThat(lottoResult.getLottoRanks().get(Rank.SECOND_RANK)).isEqualTo(0);
        assertThat(lottoResult.getLottoRanks().get(Rank.THIRD_RANK)).isEqualTo(0);
        assertThat(lottoResult.getLottoRanks().get(Rank.FOURTH_RANK)).isEqualTo(1);
        assertThat(lottoResult.getLottoRanks().get(Rank.FIFTH_RANK)).isEqualTo(0);
    }

    @Test
    void 로또_수익률을_계산한다() {
        int price = 3500;
        List<Lotto> lottos = List.of(
                Lotto.ofNumbers(List.of(1,2,3,4,5,6)),
                Lotto.ofNumbers(List.of(3,4,5,6,7,8)),
                Lotto.ofNumbers(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(price);
        lottoBuyer.buyAutomaticLottos(price / LottoStore.COST);

        Lotto winningNumbers = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);

        assertThat(lottoBuyer.calculateEarningRate(lottoResult.getLottoRanks())).isEqualTo(666683.33);
    }
}

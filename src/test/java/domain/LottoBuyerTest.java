package domain;

import dto.LottoResult;
import dto.WinningLotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoBuyerTest {

    @ValueSource(ints = {10000, 2000, 4000})
    @ParameterizedTest
    void 로또를_구매금액만큼_자동으로_구매한다(int money) {
        // given
        LottoBuyer lottoBuyer = new LottoBuyer(money, new LottoStore());

        // when
        lottoBuyer.buyAuto();

        // then
        assertThat(lottoBuyer.getAutoLottos().size()).isEqualTo(money / LottoStore.LOTTO_COST);
    }

    @Test
    void 로또를_수동으로_구매한다() {
        // given
        int money = 2300;
        LottoBuyer lottoBuyer = new LottoBuyer(money, new LottoStore());
        List<Lotto> manualLottos = List.of(
                Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.ofManual(List.of(7, 8, 9, 10, 11, 12))
                );

        // when
        lottoBuyer.buyManual(manualLottos);

        // then
        assertThat(lottoBuyer.getManualLottos()).containsExactlyElementsOf(manualLottos);
    }

    @Test
    void 로또_당첨_등수를_확인한다() {
        // given
        List<Lotto> manual = List.of(
                Lotto.ofManual(List.of(1,2,3,4,5,6)),
                Lotto.ofManual(List.of(3,4,5,6,7,8)),
                Lotto.ofManual(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(3500,  new LottoStore());
        lottoBuyer.buyManual(manual);

        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(45));

        // when
        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);
        Map<LottoRank, Integer> lottoRanks = lottoResult.getLottoRanks();

        // then
        assertThat(lottoRanks.get(LottoRank.FIRST_RANK)).isEqualTo(1);
        assertThat(lottoRanks.get(LottoRank.SECOND_RANK)).isEqualTo(0);
        assertThat(lottoRanks.get(LottoRank.THIRD_RANK)).isEqualTo(0);
        assertThat(lottoRanks.get(LottoRank.FOURTH_RANK)).isEqualTo(1);
        assertThat(lottoRanks.get(LottoRank.FIFTH_RANK)).isEqualTo(0);
    }

    @Test
    void 로또_수익률을_계산한다() {
        // given
        List<Lotto> manual = List.of(
                Lotto.ofManual(List.of(1,2,3,4,5,6)),
                Lotto.ofManual(List.of(3,4,5,6,7,8)),
                Lotto.ofManual(List.of(5,6,7,8,9,10))
        );
        LottoBuyer lottoBuyer = new LottoBuyer(3500, new LottoStore());
        lottoBuyer.buyManual(manual);

        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(45));


        // when
        LottoResult lottoResult = lottoBuyer.calculateResult(winningLotto);
        Double rate = lottoResult.getEarningRate();

        // then
        assertThat(rate).isEqualTo(66668233.33);
    }
}

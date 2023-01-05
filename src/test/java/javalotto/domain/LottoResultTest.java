package javalotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void should_returnCorrectString_when_givenWinningLottoAndLottos() {
        WinningLotto winningLotto = WinningLotto.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), 7);
        PurchaseAmount purchaseAmount = PurchaseAmount.from(4000);
        LottoBuyer lottoBuyer = LottoBuyer.of(purchaseAmount, TotalLottoCount.of(LottoCount.from(4), purchaseAmount));
        List<List<Integer>> lottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 45)
        );
        lottoBuyer.purchaseLottos(LottoShop.from(RandomNumberGenerator.newInstance()), lottoNumbers);

        LottoResult lottoResult = lottoBuyer.getLottoResult(winningLotto);

        assertThat(lottoResult.toString())
                .isEqualTo("3개 일치 (5000원)- 0개\n" +
                        "4개 일치 (50000원)- 0개\n" +
                        "5개 일치 (1500000원)- 1개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원)- 1개\n" +
                        "6개 일치 (2000000000원)- 2개");
    }
}
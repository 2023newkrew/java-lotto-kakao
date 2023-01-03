package javalotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void should_returnCorrectString_when_givenWinningLottoAndLottos() {
        WinningLotto winningLotto = WinningLotto.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lottos lottos = Lottos.from(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 45))
        ));
        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        assertThat(lottoResult.toString())
                .isEqualTo("3개 일치 (5000원)- 0개\n" +
                        "4개 일치 (50000원)- 0개\n" +
                        "5개 일치 (1500000원)- 1개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원)- 1개\n" +
                        "6개 일치 (2000000000원)- 2개");
    }
}
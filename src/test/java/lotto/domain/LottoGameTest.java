package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoGameTest {
    @Test
    void 로또갯수만큼_로또가_생성된다() {
        LottoGame lottoGame = new LottoGame(
                Lotto.autoGenerateByAmounts(30),
                new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7)
        );
        assertThat(lottoGame.getLottos().size()).isEqualTo(30);
    }
}
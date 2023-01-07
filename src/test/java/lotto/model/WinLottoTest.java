package lotto.model;

import lotto.exception.DuplicatedBallNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinLottoTest {
    @Test
    void 보너스_숫자가_원래_숫자와_겹치면_예외() {
        assertThatThrownBy(() -> {
            WinLotto winLotto = new WinLotto(new LottoManual(List.of(1, 2, 3, 4, 5, 6)), new LottoBall(6));
        }).isInstanceOf(DuplicatedBallNumber.class);
    }

    @Test
    void 보너스_숫자가_원래_숫자와_안겹치면_정상실행() {
        assertThatCode(() -> {
            WinLotto winLotto = new WinLotto(new LottoManual(List.of(1, 2, 3, 4, 5, 6)), new LottoBall(7));
        }).doesNotThrowAnyException();
    }

    @Test
    void trial에_대한_결과를_잘_반환하는가() {
        WinLotto winLotto = new WinLotto(new LottoManual(List.of(1, 2, 3, 4, 5, 6)), new LottoBall(7));

        Assertions.assertThat(winLotto.compareLotto(new LottoManual(List.of(1, 2, 3, 4, 5, 7))))
                .isEqualTo(new LottoResult(5,true));

        Assertions.assertThat(winLotto.compareLotto(new LottoManual(List.of(4, 2, 3, 1, 8, 9))))
                .isEqualTo(new LottoResult(4,false));
    }
}
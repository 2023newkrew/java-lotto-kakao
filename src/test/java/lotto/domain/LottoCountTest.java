package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoCountTest {

    LottoCount lottoCount = new LottoCount(1, 2);

    @Test
    void 수동_티켓_수와_전체_티켓_수를_받는다() {
        assertThatCode(() -> new LottoCount(1, 2)).doesNotThrowAnyException();
    }

    @Test
    void 수동_티켓_수가_전체_티켓_수보다_많으면_예외가_발생한다() {
        assertThatThrownBy(() -> new LottoCount(2, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수동_티켓_발급이_가능하면_true를_반환한다() {
        assertThat(lottoCount.isManualLottoAvailable()).isTrue();
    }

    @Test
    void 수동_티켓_발급이_불가능하면_false를_반환한다() {
        lottoCount.decreaseManualCount();
        assertThat(lottoCount.isManualLottoAvailable()).isFalse();
    }

    @Test
    void 자동_티켓_발급이_가능하면_true를_반환한다() {
        assertThat(lottoCount.isAutoLottoAvailable()).isTrue();
    }

    @Test
    void 자동_티켓_발급이_불가능하면_false를_반환한다() {
        lottoCount.decreaseAutoCount();
        assertThat(lottoCount.isAutoLottoAvailable()).isFalse();
    }
}

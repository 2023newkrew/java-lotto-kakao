package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TotalResultTest {
    @Test
    void 결과_없으면_수익률_예외() {
        assertThatThrownBy(() -> {
            TotalResult totalResult = new TotalResult();

            totalResult.getReturnRatio();
        }).isInstanceOf(ArithmeticException.class);
    }

    @Test
    void 결과_있으면_정상실() {
        TotalResult totalResult = new TotalResult();

        totalResult.addResult(new LottoResult(0, false));
        totalResult.addResult(new LottoResult(2, false));
        totalResult.addResult(new LottoResult(1, false));
        totalResult.addResult(new LottoResult(0, false));
        totalResult.addResult(new LottoResult(1, false));
        totalResult.addResult(new LottoResult(1, false));
        totalResult.addResult(new LottoResult(3, false));

        Assertions.assertThat(totalResult.getReturnRatio()).isEqualTo((double)5 / 7);
    }
}

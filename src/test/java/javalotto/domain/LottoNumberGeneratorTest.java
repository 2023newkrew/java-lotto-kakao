package javalotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {

    @Test
    void should_generateLottoSuccessfully_when_validCount() {
        TotalLottoCount lottoCount = TotalLottoCount.of(LottoCount.from(0), PurchaseAmount.from((10000)));
        LottoGenerator lottoGenerator = LottoGenerator.from((minInclusive, maxExclusive, count) -> List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = lottoGenerator.generateLottos(lottoCount);
        assertThat(lottos.size()).isEqualTo(lottoCount.getTotalCount());
    }
}
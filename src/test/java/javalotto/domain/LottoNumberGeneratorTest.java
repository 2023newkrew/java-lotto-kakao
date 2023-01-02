package javalotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {

    @Test
    void should_generateLottoSuccessfully_when_validCount() {
        LottoCount lottoCount = LottoCount.withCount(10);
        LottoGenerator lottoGenerator = LottoGenerator.from((minInclusive, maxExclusive, count) -> List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottos = lottoGenerator.getLottos(lottoCount);
        assertThat(lottos).hasSize(lottoCount.getCount());
    }
}
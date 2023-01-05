package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.constant.LottoRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoCalculatorTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void 정답과_6개가_모두_일치하면_1등상을_받아야_한다(boolean hasMagicNumber) {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH, hasMagicNumber)).isEqualTo(
                LottoPrize.FIRST_PRIZE);
    }

    @Test
    void 정답과_5개가_일치하고_보너스_숫자와_일치하면_2등상을_받아야_한다() {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH - 1, true)).isEqualTo(
                LottoPrize.SECOND_PRIZE);
    }

    @Test
    void 정답과_5개가_일치하면_3등상을_받아야_한다() {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH - 1, false)).isEqualTo(
                LottoPrize.THIRD_PRIZE);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void 정답과_4개가_일치하면_4등상을_받아야_한다(boolean hasMagicNumber) {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH - 2, hasMagicNumber)).isEqualTo(
                LottoPrize.FOURTH_PRIZE);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void 정답과_3개가_일치하면_5등상을_받아야_한다(boolean hasMagicNumber) {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH - 3, hasMagicNumber)).isEqualTo(
                LottoPrize.FIFTH_PRIZE);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void 정답과_3개_미만이_일치하면_아무것도_받지_못한다(int criteria) {
        assertThat(LottoCalculator.calculatePrize(LottoRule.LENGTH - criteria, false)).isEqualTo(
                LottoPrize.NONE);
    }
}

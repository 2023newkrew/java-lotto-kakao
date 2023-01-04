package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {
    LottoNumbers winningLottoNumbers = new LottoNumbers(
            Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            )
    );
    LottoNumber bonusNumber = new LottoNumber(7);
    WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);

    @Test
    void 정답과_보너스_볼이_있어야_한다() {
        assertThatCode(() -> new WinningLotto(winningLottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 정답과_보너스_볼이_중첩되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(winningLottoNumbers, new LottoNumber(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정답과_6개가_모두_일치하면_1등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIRST_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하고_보너스_숫자와_일치하면_2등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.SECOND_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하면_3등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.THIRD_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_4개가_일치하면_4등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(44),
                new LottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FOURTH_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개가_일치하면_5등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(43),
                new LottoNumber(44),
                new LottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIFTH_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개_미만이_일치하면_아무것도_받지_못한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(40),
                new LottoNumber(41),
                new LottoNumber(42),
                new LottoNumber(43),
                new LottoNumber(44),
                new LottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.NONE, 1);
                }}
        );
        assertThat(winningLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }
}

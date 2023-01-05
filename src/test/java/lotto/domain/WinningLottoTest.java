package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {
    LottoNumbers winningLottoNumbers = new LottoNumbers(
            Arrays.asList(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
            )
    );
    LottoNumber bonusNumber = LottoNumber.from(7);
    WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);

    @Test
    void 정답과_보너스_볼이_있어야_한다() {
        assertThatCode(() -> new WinningLotto(winningLottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 정답과_보너스_볼이_중첩되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(winningLottoNumbers, LottoNumber.from(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정답과_6개가_모두_일치하면_1등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIRST_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하고_보너스_숫자와_일치하면_2등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.SECOND_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하면_3등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.THIRD_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_4개가_일치하면_4등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FOURTH_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개가_일치하면_5등상을_받아야_한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(43),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIFTH_PRIZE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개_미만이_일치하면_아무것도_받지_못한다() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.from(40),
                LottoNumber.from(41),
                LottoNumber.from(42),
                LottoNumber.from(43),
                LottoNumber.from(44),
                LottoNumber.from(45)
        );

        LottoNumbers userLotto = new LottoNumbers(lottoNumbers);
        LottoPrizeCountMap expected = new LottoPrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.NONE, 1);
                }}
        );
        assertThat(winningLotto.getLottoPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }
}

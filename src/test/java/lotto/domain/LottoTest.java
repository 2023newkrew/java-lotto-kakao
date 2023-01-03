package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class LottoTest {
    LottoNumbers answerlottoNumbers = new LottoNumbers(
            Arrays.asList(
                    new SingleLottoNumber(1),
                    new SingleLottoNumber(2),
                    new SingleLottoNumber(3),
                    new SingleLottoNumber(4),
                    new SingleLottoNumber(5),
                    new SingleLottoNumber(6)
            )
    );
    SingleLottoNumber bonusNumber = new SingleLottoNumber(7);
    Lotto answerLotto = new Lotto(answerlottoNumbers, bonusNumber);

    @Test
    void 정답과_보너스_볼이_있어야_한다() {
        assertThatCode(() -> new Lotto(answerlottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 정답과_6개가_모두_일치하면_1등상을_받아야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(6)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIRST_PRIZE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하고_보너스_숫자와_일치하면_2등상을_받아야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(7)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.SECOND_PRIZE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_5개가_일치하면_3등상을_받아야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.THIRD_PRIZE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_4개가_일치하면_4등상을_받아야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(44),
                new SingleLottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FOURTH_PRIZE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개가_일치하면_5등상을_받아야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(43),
                new SingleLottoNumber(44),
                new SingleLottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIFTH_PRIZE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }

    @Test
    void 정답과_3개_미만이_일치하면_아무것도_받지_못한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(40),
                new SingleLottoNumber(41),
                new SingleLottoNumber(42),
                new SingleLottoNumber(43),
                new SingleLottoNumber(44),
                new SingleLottoNumber(45)
        );

        LottoNumbers userLotto = new LottoNumbers(singleLottoNumbers);
        PrizeCountMap expected = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.NONE, 1);
                }}
        );
        assertThat(answerLotto.getPrizeCountMap(List.of(userLotto))).isEqualTo(expected);
    }
}

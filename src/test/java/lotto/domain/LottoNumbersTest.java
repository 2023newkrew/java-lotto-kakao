package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumbersTest {

    @Test
    void 입력되는_로또_번호가_6개여야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(6)
        );

        assertThatCode(() -> new LottoNumbers(singleLottoNumbers)).doesNotThrowAnyException();
    }

    @Test
    void 입력되는_로또_번호가_6개가_아니면_예외가_발생한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(6),
                new SingleLottoNumber(7)
        );

        assertThatThrownBy(() -> new LottoNumbers((singleLottoNumbers))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복된_로또_번호가_있으면_예외가_발생한다() {
        List<SingleLottoNumber> duplicatedLottoNumbers = Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(6),
                new SingleLottoNumber(6)
        );

        assertThatThrownBy(() -> new LottoNumbers(duplicatedLottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 7, 8, 6})
    void 보너스_번호를_받아서_포함되어_있으면_true를_반환한다(int bonusNumberValue) {
        SingleLottoNumber bonusNumber = new SingleLottoNumber(bonusNumberValue);
        LottoNumbers lottoNumbers = new LottoNumbers(
                Arrays.asList(
                        new SingleLottoNumber(1),
                        new SingleLottoNumber(2),
                        new SingleLottoNumber(3),
                        new SingleLottoNumber(6),
                        new SingleLottoNumber(7),
                        new SingleLottoNumber(8)
                )
        );

        assertThat(lottoNumbers.containsLottoNumber(bonusNumber)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12, 13, 14, 15})
    void 보너스_번호를_받아서_포함되어_있지_않으면_false를_반환한다(int bonusNumberValue) {
        SingleLottoNumber bonusNumber = new SingleLottoNumber(bonusNumberValue);
        LottoNumbers lottoNumbers = new LottoNumbers(
                Arrays.asList(
                        new SingleLottoNumber(1),
                        new SingleLottoNumber(2),
                        new SingleLottoNumber(3),
                        new SingleLottoNumber(6),
                        new SingleLottoNumber(7),
                        new SingleLottoNumber(8)
                )
        );

        assertThat(lottoNumbers.containsLottoNumber(bonusNumber)).isFalse();
    }

    @Test
    void 사용자의_로또_번호들을_받아서_일치하는_번호의_개수를_반환한다() {
        LottoNumbers answerLottos = new LottoNumbers(Arrays.asList(
                new SingleLottoNumber(1),
                new SingleLottoNumber(2),
                new SingleLottoNumber(3),
                new SingleLottoNumber(4),
                new SingleLottoNumber(5),
                new SingleLottoNumber(6)
        ));

        LottoNumbers userLottos = new LottoNumbers(
                Arrays.asList(
                        new SingleLottoNumber(1),
                        new SingleLottoNumber(2),
                        new SingleLottoNumber(3),
                        new SingleLottoNumber(6),
                        new SingleLottoNumber(7),
                        new SingleLottoNumber(8)
                )
        );

        assertThat(answerLottos.countMatchNumber(userLottos)).isEqualTo(4);
    }
}

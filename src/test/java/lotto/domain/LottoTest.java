package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

    @Test
    void 입력되는_로또_번호가_6개여야_한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                SingleLottoNumber.from(1),
                SingleLottoNumber.from(2),
                SingleLottoNumber.from(3),
                SingleLottoNumber.from(4),
                SingleLottoNumber.from(5),
                SingleLottoNumber.from(6)
        );

        assertThatCode(() -> new Lotto(singleLottoNumbers)).doesNotThrowAnyException();
    }

    @Test
    void 입력되는_로또_번호가_6개가_아니면_예외가_발생한다() {
        List<SingleLottoNumber> singleLottoNumbers = Arrays.asList(
                SingleLottoNumber.from(1),
                SingleLottoNumber.from(2),
                SingleLottoNumber.from(3),
                SingleLottoNumber.from(4),
                SingleLottoNumber.from(5),
                SingleLottoNumber.from(6),
                SingleLottoNumber.from(7)
        );

        assertThatThrownBy(() -> new Lotto((singleLottoNumbers))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복된_로또_번호가_있으면_예외가_발생한다() {
        List<SingleLottoNumber> duplicatedLottoNumbers = Arrays.asList(
                SingleLottoNumber.from(1),
                SingleLottoNumber.from(2),
                SingleLottoNumber.from(3),
                SingleLottoNumber.from(4),
                SingleLottoNumber.from(6),
                SingleLottoNumber.from(6)
        );

        assertThatThrownBy(() -> new Lotto(duplicatedLottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 7, 8, 6})
    void 보너스_번호를_받아서_포함되어_있으면_true를_반환한다(int bonusNumberValue) {
        SingleLottoNumber bonusNumber = SingleLottoNumber.from(bonusNumberValue);
        Lotto lotto = new Lotto(
                Arrays.asList(
                        SingleLottoNumber.from(1),
                        SingleLottoNumber.from(2),
                        SingleLottoNumber.from(3),
                        SingleLottoNumber.from(6),
                        SingleLottoNumber.from(7),
                        SingleLottoNumber.from(8)
                )
        );

        assertThat(lotto.containsLottoNumber(bonusNumber)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12, 13, 14, 15})
    void 보너스_번호를_받아서_포함되어_있지_않으면_false를_반환한다(int bonusNumberValue) {
        SingleLottoNumber bonusNumber = SingleLottoNumber.from(bonusNumberValue);
        Lotto lotto = new Lotto(
                Arrays.asList(
                        SingleLottoNumber.from(1),
                        SingleLottoNumber.from(2),
                        SingleLottoNumber.from(3),
                        SingleLottoNumber.from(6),
                        SingleLottoNumber.from(7),
                        SingleLottoNumber.from(8)
                )
        );

        assertThat(lotto.containsLottoNumber(bonusNumber)).isFalse();
    }

    @Test
    void 로또_번호를_받아서_일치하는_번호의_개수를_반환한다() {
        Lotto lotto = new Lotto(Arrays.asList(
                SingleLottoNumber.from(1),
                SingleLottoNumber.from(2),
                SingleLottoNumber.from(3),
                SingleLottoNumber.from(4),
                SingleLottoNumber.from(5),
                SingleLottoNumber.from(6)
        ));

        Lotto userLotto = new Lotto(
                Arrays.asList(
                        SingleLottoNumber.from(1),
                        SingleLottoNumber.from(2),
                        SingleLottoNumber.from(3),
                        SingleLottoNumber.from(6),
                        SingleLottoNumber.from(7),
                        SingleLottoNumber.from(8)
                )
        );

        assertThat(lotto.countMatchNumber(userLotto)).isEqualTo(4);
    }
}

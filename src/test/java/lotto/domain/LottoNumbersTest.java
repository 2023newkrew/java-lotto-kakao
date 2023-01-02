package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AnswerLottoTest {

    Lotto answerlottoNumbers = new Lotto(
            Arrays.asList(
                    SingleLottoNumber.from(1),
                    SingleLottoNumber.from(2),
                    SingleLottoNumber.from(3),
                    SingleLottoNumber.from(4),
                    SingleLottoNumber.from(5),
                    SingleLottoNumber.from(6)
            )
    );

    SingleLottoNumber bonusNumber = SingleLottoNumber.from(7);

    @Test
    void 정답과_보너스_볼이_있어야_한다() {
        assertThatCode(() -> new AnswerLotto(answerlottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 정답_안에_보너스_볼과_같은_번호가_포함되어_있으면_에러가_발생한다() {
        Lotto answerlottoNumbers = new Lotto(
                Arrays.asList(
                        SingleLottoNumber.from(1),
                        SingleLottoNumber.from(2),
                        SingleLottoNumber.from(3),
                        SingleLottoNumber.from(4),
                        SingleLottoNumber.from(5),
                        SingleLottoNumber.from(6)
                )
        );
        SingleLottoNumber bonusNumber = SingleLottoNumber.from(6);

        assertThatThrownBy(() -> new AnswerLotto(answerlottoNumbers, bonusNumber)).isInstanceOf(
                IllegalArgumentException.class);
    }
}

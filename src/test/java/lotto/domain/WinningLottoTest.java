package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void 정답과_보너스_볼이_있어야_한다() {
        assertThatCode(() -> new WinningLotto(winningLottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 정답과_보너스_볼이_중첩되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(winningLottoNumbers, LottoNumber.from(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}

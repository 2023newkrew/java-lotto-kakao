package domain.lotto;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호는 1부터 45 사이이다.")
    void validateRange() {
        assertThatNoException().isThrownBy(() -> new LottoNumber(2));
    }

    @Test
    @DisplayName("범위 밖의 로또 번호를 입력하면 예외가 발생한다.")
    void validateRange_throw() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumber(50));
    }
}

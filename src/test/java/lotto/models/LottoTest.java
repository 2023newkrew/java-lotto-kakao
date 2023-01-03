package lotto.models;

import static lotto.common.LottoConfiguration.LOTTO_COUNT;
import static lotto.common.LottoConfiguration.MAX_VALUE;
import static lotto.common.LottoConfiguration.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Nested
    @DisplayName("유효한 로또 생성 테스트")
    public class CreateValidLotto {
        @Test
        @DisplayName("로또를 생성할 수 있다.")
        public void testCreateLotto() {
            Lotto lotto = new Lotto(Arrays.asList(4, 2, 3, 1, 5, 6));
            assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }
    }

    @Nested
    @DisplayName("유효하지 않은 로또 생성 테스트")
    public class CreateInvalidLotto {
        @Test
        @DisplayName("숫자 수가 6개가 아닌 로또를 생성하면 예외가 발생한다.")
        public void testInvalidSizeLotto() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5)))
                    .withMessage("로또는" + LOTTO_COUNT + "개의 숫자로 이루어져야 합니다.");
        }

        @Test
        @DisplayName("범위를 벗어난 숫자가 포함된 로또를 생성하면 예외가 발생한다.")
        public void testInvalidRangeLotto() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 105, 6)))
                    .withMessage("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }

        @Test
        @DisplayName("중복된 숫자가 포함된 로또를 생성하면 에러가 발생한다.")
        public void testLottoIncludeDuplicatedNumber() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 5)))
                    .withMessage("로또 번호에 중복된 번호가 있어서는 안됩니다.");
        }
    }
}
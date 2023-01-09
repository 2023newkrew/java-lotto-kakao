package domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class LottoWinningNumbersTest {

    LottoNumbers lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new LottoNumbers(Set.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)

        ));
    }

    @DisplayName("당첨 번호 6개와 보너스 번호 1개를 주입받아서 생성한다")
    @Test
    void create() {
        LottoNumber bonusNumber = new LottoNumber(10);

        assertThatNoException()
                .isThrownBy(() -> new LottoWinningNumbers(lottoNumbers, bonusNumber));
    }

    @DisplayName("당첨 번호 중 보너스 번호와 중복된 번호가 있으면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void create_throw(int number) {
        LottoNumber bonusNumber = new LottoNumber(number);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoWinningNumbers(lottoNumbers, bonusNumber));
    }
}

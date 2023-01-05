package lotto.domain;

import static lotto.domain.Lotto.makeLotto;
import static lotto.domain.Lotto.makeLotto;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {


    @Test
    @DisplayName("Lotto는_사이즈가_6인_IntegerList로_생성이_가능하다")
    void Lotto는_사이즈가_6인_IntegerList로_생성이_가능하다() {
        assertThatCode(() -> makeLotto(List.of(1, 2, 3, 4, 5, 6))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Lotto는_사이즈가6이_아닌_IntegerList로_생성이_불가능하다")
    void Lotto는_사이즈가6이_아닌_IntegerList로_생성이_불가능하다() {
        assertThatThrownBy(() -> makeLotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Lotto는_자동으로_생성이_가능하다")
    void Lotto는_자동으로_생성이_가능하다() {
        assertThatCode(() -> Lotto.makeLotto(new RandomGeneratePolicy())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Lotto는_출력_포맷을_제공해야한다")
    void Lotto는_출력_포맷을_제공해야한다() {
        assertThat(makeLotto(List.of(1, 2, 3, 4, 5, 6)).lottoToString()).isEqualTo(
                String.format("[%d, %d, %d, %d, %d, %d]", 1, 2, 3, 4, 5, 6));
    }

}

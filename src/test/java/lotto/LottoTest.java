package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {
    @DisplayName("중복된 숫자로 리스트 받으면 예외 발생")
    @Test
    void duplicateTest() {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1, 1, 1, 1, 1, 1)));
    }

    @DisplayName("입력 숫자가 6개가 아닌 리스트 받으면 예외 발생")
    @Test
    void sizeTest() {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1)));
    }
}

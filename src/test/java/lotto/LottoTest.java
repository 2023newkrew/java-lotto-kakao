package lotto;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @DisplayName("잘못된 숫자로 생성 시 예외 발생")
    @ParameterizedTest
    @MethodSource
    void consistByInvalidNumbers(int[] numbers, String message) {
        Assertions.assertThatThrownBy(() -> Lotto.create(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    static List<Arguments> consistByInvalidNumbers() {
        return List.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, "로또 번호는 중복이 없는 6자리 숫자입니다."),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7}, "로또 번호는 중복이 없는 6자리 숫자입니다."),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 1}, "로또 번호는 중복이 없는 6자리 숫자입니다.")
        );
    }
}

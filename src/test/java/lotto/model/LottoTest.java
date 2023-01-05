package lotto.model;

import java.util.List;

import java.util.Set;
import lotto.model.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("로또에 포함된 숫자를 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void hasNumber(int number) {
        Lotto lotto = Lotto.create(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(lotto.hasNumber(new LottoNumber(number))).isTrue();
    }

    @DisplayName("로또에 포함되지 않은 숫자를 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 20, 45})
    void notHasNumber(int number) {
        Lotto lotto = Lotto.create(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(lotto.hasNumber(new LottoNumber(number))).isFalse();
    }

    @DisplayName("동일한 로또 번호의 개수를 구한다.")
    @ParameterizedTest
    @MethodSource
    void countOverlappedNumbers(Lotto other, int expectedCount) {
        Lotto own = Lotto.create(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(own.countOverlappedNumber(other)).isEqualTo(expectedCount);
    }

    static List<Arguments> countOverlappedNumbers() {
        return List.of(
                Arguments.of(Lotto.create(1, 7, 8, 9, 10, 11), 1),
                Arguments.of(Lotto.create(1, 2, 13, 14, 15, 16), 2),
                Arguments.of(Lotto.create(1, 2, 3, 24, 25, 26), 3),
                Arguments.of(Lotto.create(1, 2, 3, 4, 35, 36), 4),
                Arguments.of(Lotto.create(1, 2, 3, 4, 5, 45), 5),
                Arguments.of(Lotto.create(1, 2, 3, 4, 5, 6), 6)
        );
    }
}

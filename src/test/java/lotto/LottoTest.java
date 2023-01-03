package lotto;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.TestUtil.toLottoNumbers;

class LottoTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("빈 숫자로 생성 시 예외 발생")
        @ParameterizedTest
        @NullAndEmptySource
        void should_throwException_when_givenNullOrEmpty(List<LottoNumber> lottoNumbers) {
            Assertions.assertThatThrownBy(() -> Lotto.from(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복이 없는 6자리 숫자입니다.");
        }

        @DisplayName("잘못된 숫자들로 생성 시 예외 발생")
        @ParameterizedTest
        @MethodSource
        void should_throwException_when_givenNumbers(List<Integer> numbers) {
            List<LottoNumber> lottoNumbers = toLottoNumbers(numbers);

            Assertions.assertThatThrownBy(() -> Lotto.from(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복이 없는 6자리 숫자입니다.");
        }

        List<Arguments> should_throwException_when_givenNumbers() {
            return List.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 1))
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class countCommonNumber {

        @DisplayName("두 로또의 공통 숫자 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnCommonCount_when_givenTwoNumbers(List<Integer> numbers, List<Integer> otherNumbers, long expected) {
            Lotto lotto = Lotto.from(toLottoNumbers(numbers));
            Lotto other = Lotto.from(toLottoNumbers(otherNumbers));

            long commonCount = lotto.countCommonNumber(other);

            Assertions.assertThat(commonCount).isEqualTo(expected);
        }

        List<Arguments> should_returnCommonCount_when_givenTwoNumbers() {
            List<Integer> originNumbers = List.of(1, 2, 3, 4, 5, 6);
            return List.of(
                    Arguments.of(originNumbers, List.of(7, 8, 9, 10, 11, 12), 0L),
                    Arguments.of(originNumbers, List.of(1, 8, 9, 10, 11, 12), 1L),
                    Arguments.of(originNumbers, List.of(1, 2, 9, 10, 11, 12), 2L),
                    Arguments.of(originNumbers, List.of(1, 2, 3, 10, 11, 12), 3L),
                    Arguments.of(originNumbers, List.of(1, 2, 3, 4, 11, 12), 4L),
                    Arguments.of(originNumbers, List.of(1, 2, 3, 4, 5, 12), 5L),
                    Arguments.of(originNumbers, List.of(1, 2, 3, 4, 5, 6), 6L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class hasNumber {

        @DisplayName("로또 번호 안에 해당 숫자가 있는지 확인")
        @ParameterizedTest
        @CsvSource(value = {
                "1, true",
                "2, true",
                "3, true",
                "4, true",
                "5, true",
                "6, true",
                "7, false",
        })
        void should_returnHasNumber_when_givenNumber(int number, boolean hasNumber) {
            Lotto lotto = Lotto.from(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

            boolean actual = lotto.hasNumber(LottoNumber.valueOf(number));

            Assertions.assertThat(actual).isEqualTo(hasNumber);
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getLottoNumbers {

        @DisplayName("로또 숫자 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnNumbers_when_call(List<Integer> numbers) {
            Lotto lotto = Lotto.from(toLottoNumbers(numbers));

            List<Integer> actual = lotto.getLottoNumbers();

            Assertions.assertThatCollection(actual).isEqualTo(numbers);
        }

        List<Arguments> should_returnNumbers_when_call() {
            return List.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6)),
                    Arguments.of(List.of(7, 8, 9, 10, 11, 12))
            );
        }
    }
}

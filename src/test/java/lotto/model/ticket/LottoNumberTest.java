package lotto.model.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class LottoNumberTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class of {

        @DisplayName("잘못된 숫자로 생성 시 예외 발생")
        @ParameterizedTest
        @NullAndEmptySource
        @MethodSource
        void should_throwException_when_givenInvalidNumbers(Set<Integer> numbers) {
            Set<SingleLottoNumber> singleLottoNumbers = toSingleLottoNumbers(numbers);

            Assertions.assertThatThrownBy(() -> LottoNumber.of(singleLottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6자리 숫자입니다.");
        }

        List<Arguments> should_throwException_when_givenInvalidNumbers() {
            return List.of(
                    Arguments.of(Set.of(1, 2, 3)),
                    Arguments.of(Set.of(1, 2, 3, 4, 5)),
                    Arguments.of(Set.of(1, 2, 3, 4, 5, 6, 7))
            );
        }


        @DisplayName("정상적인 숫자일 경우 로또 번호 발행")
        @ParameterizedTest
        @MethodSource
        void should_returnLottoNumber_when_givenValidNumbers(Set<Integer> numbers) {
            Set<SingleLottoNumber> singleLottoNumbers = toSingleLottoNumbers(numbers);

            LottoNumber lottoNumber = LottoNumber.of(singleLottoNumbers);
            Set<Integer> actual = lottoNumber.stream()
                    .map(SingleLottoNumber::intValue)
                    .collect(Collectors.toSet());

            Assertions.assertThatCollection(actual).isEqualTo(numbers);
        }

        List<Arguments> should_returnLottoNumber_when_givenValidNumbers() {
            return List.of(
                    Arguments.of(Set.of(1, 2, 3, 4, 5, 6)),
                    Arguments.of(Set.of(11, 12, 13, 14, 15, 16)),
                    Arguments.of(Set.of(40, 41, 42, 43, 44, 45))
            );
        }
    }

    public static Set<SingleLottoNumber> toSingleLottoNumbers(Set<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            return null;
        }

        return numbers.stream()
                .map(SingleLottoNumber::valueOf)
                .collect(Collectors.toSet());
    }

}
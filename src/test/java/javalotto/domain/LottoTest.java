package javalotto.domain;

import javalotto.exception.lotto.LottoInvalidSizeException;
import javalotto.exception.lotto.LottoNumberDuplicateException;
import javalotto.exception.lotto.LottoNumberOutOfRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void should_createSuccessfully_when_valid() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(numbers);
        assertThat(lotto).isNotNull();
    }

    @Test
    void should_storeNumbersInSortedOrder() {
        List<Integer> numbers = List.of(2, 1, 3, 4, 5, 6);
        String sortedNumbersLottoString = "[1, 2, 3, 4, 5, 6]";

        Lotto lotto = Lotto.from(numbers);

        assertThat(lotto.toString()).isEqualTo(sortedNumbersLottoString);
    }

    @ParameterizedTest
    @MethodSource
    void should_throwException_when_outOfRangeNumber(List<Integer> numbers) {
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(LottoNumberOutOfRangeException.class);
    }

    static Stream<Arguments> should_throwException_when_outOfRangeNumber() {
        return Stream.of(
                Arguments.of(List.of(-1, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46))
        );
    }

    @Test
    void should_throwException_when_duplicateNumber() {
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(LottoNumberDuplicateException.class);
    }

    @Test
    void should_throwException_when_invalidSizeNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(LottoInvalidSizeException.class);
    }

    @Test
    void should_toStringReturnValidFormat() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(numbers);

        String toStringValue = lotto.toString();

        assertThat(toStringValue).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
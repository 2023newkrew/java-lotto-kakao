package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumberListTest {

    @DisplayName("6개의 중복되지 않은 숫자 배열 생성")
    @ParameterizedTest
    @MethodSource("getGenerateLottoNumbersData")
    public void generate_lotto_numbers(List<Integer> numbers) {
        Assertions.assertThatCode(() -> new LottoNumberList(numbers)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> getGenerateLottoNumbersData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43))
        );
    }

    @DisplayName("6개 중 중복된 숫자 존재시 실패")
    @ParameterizedTest
    @MethodSource("getFailToGenerateDuplicatedLottoNumbersData")
    public void fail_to_generate_duplicated_lotto_numbers(List<Integer> numbers) {
        Assertions.assertThatThrownBy(() -> new LottoNumberList(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getFailToGenerateDuplicatedLottoNumbersData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 1, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(41, 6, 12, 21, 41, 43))
        );
    }

    @DisplayName("6개 이외의 숫자 배열 생성시 실패")
    @ParameterizedTest
    @MethodSource("getFailToGenerateLottoNumbersOtherThan6Data")
    public void fail_to_generate_lotto_numbers_other_than_6(List<Integer> numbers) {
        Assertions.assertThatThrownBy(() -> new LottoNumberList(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getFailToGenerateLottoNumbersOtherThan6Data() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(6, 12, 21, 41, 43, 44, 45))
        );
    }

    @DisplayName("숫자가 숫자 배열에 포함되어 있는지 확인")
    @ParameterizedTest
    @MethodSource("getCheckLottoNumberIncludedInLottoNumbersData")
    public void check_lotto_number_included_in_lotto_numbers(LottoNumberList lottoNumberList,
                                                             LottoNumber lottoNumber, boolean expected) {
        Assertions.assertThat(lottoNumberList.hasNumber(lottoNumber)).isEqualTo(expected);
    }

    private static Stream<Arguments> getCheckLottoNumberIncludedInLottoNumbersData() {
        return Stream.of(
                Arguments.of(new LottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(3), true),
                Arguments.of(new LottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(33), false)
        );
    }
}

package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumberSetTest {

    @DisplayName("6개의 중복되지 않은 숫자 배열 생성")
    @ParameterizedTest
    @MethodSource("getGenerateLottoNumberSetData")
    public void generateLottoNumberSet(List<Integer> numberList) {
        Assertions.assertThatCode(() -> new LottoNumberSet(numberList)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> getGenerateLottoNumberSetData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43))
        );
    }

    @DisplayName("6개 중 중복된 숫자 존재시 실패")
    @ParameterizedTest
    @MethodSource("getFailToGenerateDuplicatedLottoNumberSetData")
    public void failToGenerateDuplicatedLottoNumberSet(List<Integer> numberList) {
        Assertions.assertThatThrownBy(() -> new LottoNumberSet(numberList))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getFailToGenerateDuplicatedLottoNumberSetData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 1, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(41, 6, 12, 21, 41, 43))
        );
    }

    @DisplayName("6개 이외의 숫자 배열 생성시 실패")
    @ParameterizedTest
    @MethodSource("getFailToGenerateLottoNumberSetOtherThan6Data")
    public void failToGenerateLottoNumberSetOtherThan6(List<Integer> numberList) {
        Assertions.assertThatThrownBy(() -> new LottoNumberSet(numberList))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getFailToGenerateLottoNumberSetOtherThan6Data() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(6, 12, 21, 41, 43, 44, 45))
        );
    }

    @DisplayName("숫자가 숫자 배열에 포함되어 있는지 확인")
    @ParameterizedTest
    @MethodSource("getCheckLottoNumberIncludedInLottoNumberSetData")
    public void checkLottoNumberIncludedInLottoNumberSet(LottoNumberSet lottoNumberSet,
            LottoNumber lottoNumber, boolean expected) {
        Assertions.assertThat(lottoNumberSet.hasNumber(lottoNumber)).isEqualTo(expected);
    }

    private static Stream<Arguments> getCheckLottoNumberIncludedInLottoNumberSetData() {
        return Stream.of(
                Arguments.of(new LottoNumberSet(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.from(3), true),
                Arguments.of(new LottoNumberSet(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.from(33), false)
        );
    }
}

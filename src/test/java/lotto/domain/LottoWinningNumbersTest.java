package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.ticket.LottoWinningNumberList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoWinningNumbersTest {

    @DisplayName("6개의 숫자 배열에 1개의 보너스 볼을 가진 당첨 번호 생성")
    @ParameterizedTest
    @MethodSource("getCreateWinningNumbersWith6NumbersAnd1BonusNumber")
    public void createWinningNumbersWith6NumbersAnd1BonusNumber(
            List<Integer> numberList, int bonusNumber) {
        Assertions.assertThatCode(() -> new LottoWinningNumberList(numberList, bonusNumber))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> getCreateWinningNumbersWith6NumbersAnd1BonusNumber() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43), 8)
        );
    }

    @DisplayName("보너스 볼이 중복된 당첨 번호 생성시 실패")
    @ParameterizedTest
    @MethodSource("getCreateWinningNumbersWithDuplicatedBonusNumberData")
    public void createWinningNumbersWithDuplicatedBonusNumber(
            List<Integer> numberList, int bonusNumber) {
        Assertions.assertThatThrownBy(() -> new LottoWinningNumberList(numberList, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCreateWinningNumbersWithDuplicatedBonusNumberData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43), 12)
        );
    }
}

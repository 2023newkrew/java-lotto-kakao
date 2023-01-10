package lotto.model.ranking;

import lotto.TestUtil;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.SingleLottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;

class WinningNumbersTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class of {

        @DisplayName("당첨 번호와 보너스가 없거나 중복될 경우 예외 발생")
        @ParameterizedTest
        @MethodSource
        void should_throwException_when_invalidNumberOrBonus(LottoNumber winningNumber, SingleLottoNumber bonus, String message) {
            Assertions.assertThatThrownBy(() -> WinningNumbers.of(winningNumber, bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(message);
        }

        List<Arguments> should_throwException_when_invalidNumberOrBonus() {
            LottoNumber winningNumber = LottoNumber.createByRandom();
            SingleLottoNumber duplicatedBonus = winningNumber.stream().findFirst().orElse(null);

            return List.of(
                    Arguments.of(null, null, "당첨 번호가 없습니다."),
                    Arguments.of(null, duplicatedBonus, "당첨 번호가 없습니다."),
                    Arguments.of(winningNumber, null, "추가 번호가 없습니다."),
                    Arguments.of(winningNumber, duplicatedBonus, "중복된 당첨 번호는 생성할 수 없습니다.")
            );
        }

        @DisplayName("당첨 번호 생성 성공")
        @ParameterizedTest
        @MethodSource
        void should_returnWinningNumber_when_givenValidNumberAndBonus(Set<Integer> numbers, int bonusNumber) {
            LottoNumber winningNumber = LottoNumber.of(TestUtil.toSingleLottoNumbers(numbers));
            SingleLottoNumber bonus = SingleLottoNumber.valueOf(bonusNumber);

            WinningNumbers winningNumbers = WinningNumbers.of(winningNumber, bonus);

            Assertions.assertThat(winningNumbers).isNotNull();
        }

        List<Arguments> should_returnWinningNumber_when_givenValidNumberAndBonus() {
            return List.of(
                    Arguments.of(Set.of(1, 2, 3, 4, 5, 6), 7),
                    Arguments.of(Set.of(40, 41, 42, 43, 44, 45), 39)
            );
        }
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class rank {

        @DisplayName("로또 티켓의 등수 판정")
        @ParameterizedTest
        @MethodSource
        void should_returnRanking_when_givenNumbers(WinningNumbers winningNumbers, Set<Integer> numbers, LottoRanking ranking) {
            LottoNumber lotto = LottoNumber.of(TestUtil.toSingleLottoNumbers(numbers));

            LottoRanking actual = winningNumbers.rank(lotto);

            Assertions.assertThat(actual).isEqualTo(ranking);
        }

        List<Arguments> should_returnRanking_when_givenNumbers() {
            Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
            LottoNumber winningNumber = LottoNumber.of(TestUtil.toSingleLottoNumbers(numbers));
            SingleLottoNumber bonus = SingleLottoNumber.valueOf(7);
            WinningNumbers winningNumbers = WinningNumbers.of(winningNumber, bonus);

            return List.of(
                    Arguments.of(winningNumbers, Set.of(8, 9, 10, 11, 12, 13), LottoRanking.NOTHING),
                    Arguments.of(winningNumbers, Set.of(4, 5, 6, 7, 8, 9), LottoRanking.FIFTH),
                    Arguments.of(winningNumbers, Set.of(3, 4, 5, 6, 7, 8), LottoRanking.FOURTH),
                    Arguments.of(winningNumbers, Set.of(3, 4, 5, 6, 8, 2), LottoRanking.THIRD),
                    Arguments.of(winningNumbers, Set.of(3, 4, 5, 6, 7, 2), LottoRanking.SECOND),
                    Arguments.of(winningNumbers, numbers, LottoRanking.FIRST)
            );
        }
    }
}
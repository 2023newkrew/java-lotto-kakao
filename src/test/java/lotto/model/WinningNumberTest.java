package lotto.model;

import java.util.List;

import lotto.TestUtil;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.LottoNumber;
import lotto.model.prize.Prize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.TestUtil.toLottoNumbers;

class WinningNumberTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("당첨 번호와 보너스가 중복될 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        void should_throwException_when_givenDuplicatedBonus(int bonus) {
            Lotto lotto = Lotto.from(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
            LottoNumber bonusNumber = LottoNumber.valueOf(bonus);

            Assertions.assertThatThrownBy(() -> WinningNumber.from(lotto, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("중복된 당첨 번호는 생성할 수 없습니다.");
        }
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class judge {

        @DisplayName("로또의 등수 판정")
        @ParameterizedTest
        @MethodSource
        void should_returnPrize_when_givenNumbers(WinningNumber winningNumber, List<Integer> numbers, Prize prize) {
            Lotto lotto = Lotto.from(toLottoNumbers(numbers));

            Prize actual = winningNumber.judge(lotto);

            Assertions.assertThat(actual).isEqualTo(prize);
        }

        List<Arguments> should_returnPrize_when_givenNumbers() {
            List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
            Lotto answer = Lotto.from(toLottoNumbers(answerNumbers));
            LottoNumber bonus = LottoNumber.valueOf(7);
            WinningNumber winningNumber = WinningNumber.from(answer, bonus);

            return List.of(
                    Arguments.of(winningNumber, List.of(8, 9, 10, 11, 12, 13), Prize.NOTHING),
                    Arguments.of(winningNumber, List.of(4, 5, 6, 7, 8, 9), Prize.FIFTH),
                    Arguments.of(winningNumber, List.of(3, 4, 5, 6, 7, 8), Prize.FOURTH),
                    Arguments.of(winningNumber, List.of(3, 4, 5, 6, 8, 2), Prize.THIRD),
                    Arguments.of(winningNumber, List.of(3, 4, 5, 6, 7, 2), Prize.SECOND),
                    Arguments.of(winningNumber, answerNumbers, Prize.FIRST)
            );
        }
    }
}

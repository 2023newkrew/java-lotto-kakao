package lotto;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @DisplayName("당첨 번호는 중복이 없다.")
    @Test
    void duplicatedWinningNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.create(1, 2, 3, 4, 5, 6);
        LottoNumber bonus = new LottoNumber(1);

        Assertions.assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 당첨 번호는 생성할 수 없습니다.");
    }

    @DisplayName("판정한다")
    @ParameterizedTest
    @MethodSource
    void judge(WinningNumbers winningNumbers, LottoNumbers lottoNumbers, Prize expected) {
        Prize prize = winningNumbers.judge(lottoNumbers);

        Assertions.assertThat(prize).isEqualTo(expected);
    }

    static List<Arguments> judge() {
        WinningNumbers winningNumbers = new WinningNumbers(LottoNumbers.create(1, 2, 3, 4, 5, 6), new LottoNumber(7));

        return List.of(
                Arguments.of(winningNumbers, LottoNumbers.create(8, 9, 10, 11, 12, 13), Prize.NOTHING),
                Arguments.of(winningNumbers, LottoNumbers.create(4, 5, 6, 7, 8, 9), Prize.FIFTH),
                Arguments.of(winningNumbers, LottoNumbers.create(3, 4, 5, 6, 7, 8), Prize.FOURTH),
                Arguments.of(winningNumbers, LottoNumbers.create(3, 4, 5, 6, 8, 2), Prize.THIRD),
                Arguments.of(winningNumbers, LottoNumbers.create(3, 4, 5, 6, 7, 2), Prize.SECOND),
                Arguments.of(winningNumbers, LottoNumbers.create(1, 2, 3, 4, 5, 6), Prize.FIRST)
        );
    }
}

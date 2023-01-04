package lotto;

import lotto.model.Lotto;
import lotto.model.MatchedResult;
import lotto.model.WinningNumbers;
import lotto.model.errors.LottoDuplicatedNumberException;
import lotto.model.errors.LottoOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class WinningNumbersTest {

    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        winningNumbers = new WinningNumbers(
                List.of(1, 3, 5, 7, 9, 11),
                27
        );
    }

    @Test
    @DisplayName("당첨 번호와 로또 번호 비교 결과")
    void matched_result_test() {
        Lotto lotto = Lotto.of(1, 2, 3, 4, 5, 6);
        MatchedResult matchedResult = winningNumbers.check(lotto);
        assertEquals(matchedResult, new MatchedResult(
                3, false
        ));
    }

    @Test
    @DisplayName("당첨 번호 길이 테스트")
    void lotto_length_test() {
        assertThrowsExactly(LottoOutOfRangeException.class, () -> new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 6, 7),
                8));
    }

    @Test
    @DisplayName("당첨 번호 범위 테스트")
    void lotto_range_test() {
        assertThrowsExactly(LottoOutOfRangeException.class, () -> new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 46),
                8));

        assertThrowsExactly(LottoOutOfRangeException.class, () -> new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 6),
                46));
    }

    @Test
    @DisplayName("당첨 번호 중복 테스트")
    void lotto_duplicate_test() {
        assertThrowsExactly(LottoDuplicatedNumberException.class, () -> new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 5),
                8));

        assertThrowsExactly(LottoDuplicatedNumberException.class, () -> new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 6),
                3));
    }
}

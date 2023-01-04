package lotto;

import lotto.model.Lotto;
import lotto.model.MatchedResult;
import lotto.model.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        MatchedResult matchedResult = winningNumbers.check(lotto);
        assertEquals(matchedResult, new MatchedResult(
                3, false
        ));
    }
}

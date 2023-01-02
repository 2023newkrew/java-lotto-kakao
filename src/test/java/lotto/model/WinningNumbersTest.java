package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {
    @Test
    void 담첨_번호_생성() {
        assertThatCode(() -> new WinningNumbers(
                "1, 2, 3, 4, 5, 6",
                "7"
                )
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6, 7:7",
            "1, 2, 3, 4, 5, 6:3",
            "-1, 1, 2, 3, 4, 5:9",
            "a, 1, 2, 3, 4, 5:6"
    }, delimiter = ':')
    void 담첨_번호_생성_실패(String sixNumbers, String bonusNumber) {
        assertThatCode(() -> new WinningNumbers(
                        sixNumbers,
                        bonusNumber
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
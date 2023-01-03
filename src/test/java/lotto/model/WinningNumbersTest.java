package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

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


    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6:7:SIX",
            "1, 2, 3, 4, 5, 7:8:FIVE",
            "1, 2, 3, 4, 5, 7:6:FIVE_BONUS",
            "6, 5, 4, 3, 2, 1:7:SIX"
    }, delimiter = ':')
    void 당첨_확인(String sixNumbers, String bonusNumber, Grade result) {
        WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        assertThat(winningNumbers.matchValues(lottoTicket)).isEqualTo(result);
    }
}
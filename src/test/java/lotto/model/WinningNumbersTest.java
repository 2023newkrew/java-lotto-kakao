package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {
    @Test
    void 정상적인_입력의_경우_예외없이_생성되어야함() {
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
    void 개수나_문자나_음수인_비정상적인_입력시_예외가_발생해야함(String sixNumbers, String bonusNumber) {
        assertThatCode(() -> new WinningNumbers(
                        sixNumbers,
                        bonusNumber
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6:7:SIX",
            "1, 2, 3, 4, 5, 7:6:FIVE_BONUS",
            "1, 2, 3, 4, 5, 7:8:FIVE",
            "1, 2, 3, 4, 7, 8:5:FOUR",
            "1, 2, 3, 7, 8, 9:4:THREE"
    }, delimiter = ':')
    void 한장의_티켓에_대해_결과가_정상적으로_반환되어야함(String sixNumbers, String bonusNumber, Grade result) {
        WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(result);
    }
}
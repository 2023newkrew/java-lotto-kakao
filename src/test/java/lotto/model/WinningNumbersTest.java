package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(6)
                )
        );
    }

    @Test
    void 정상적인_입력의_경우_예외없이_생성되어야함() {
        assertThatCode(() -> new WinningNumbers(
                        lottoTicket,
                        LottoNumber.valueOf(7)
                )
        ).doesNotThrowAnyException();
    }

    @Test
    void 모두_맞춘_경우() {
        WinningNumbers winningNumbers = new WinningNumbers(
                lottoTicket,
                LottoNumber.valueOf(7)
        );
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(Grade.SIX);
    }

    @Test
    void 다섯_개와_보너스를_맞춘_경우() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket(
                        Arrays.asList(
                                LottoNumber.valueOf(1),
                                LottoNumber.valueOf(2),
                                LottoNumber.valueOf(3),
                                LottoNumber.valueOf(4),
                                LottoNumber.valueOf(5),
                                LottoNumber.valueOf(11)
                        )
                ),
                LottoNumber.valueOf(6)
        );
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(Grade.FIVE_BONUS);
    }

    @Test
    void 다섯_개_맞춘_경우() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket(
                        Arrays.asList(
                                LottoNumber.valueOf(1),
                                LottoNumber.valueOf(2),
                                LottoNumber.valueOf(3),
                                LottoNumber.valueOf(4),
                                LottoNumber.valueOf(5),
                                LottoNumber.valueOf(11)
                        )
                ),
                LottoNumber.valueOf(45)
        );
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(Grade.FIVE);
    }

    @Test
    void 네_개_맞춘_경우() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket(
                        Arrays.asList(
                                LottoNumber.valueOf(1),
                                LottoNumber.valueOf(2),
                                LottoNumber.valueOf(3),
                                LottoNumber.valueOf(4),
                                LottoNumber.valueOf(11),
                                LottoNumber.valueOf(12)
                        )
                ),
                LottoNumber.valueOf(45)
        );
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(Grade.FOUR);
    }

    @Test
    void 세_개_맞춘_경우() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket(
                        Arrays.asList(
                                LottoNumber.valueOf(1),
                                LottoNumber.valueOf(2),
                                LottoNumber.valueOf(3),
                                LottoNumber.valueOf(11),
                                LottoNumber.valueOf(12),
                                LottoNumber.valueOf(13)
                        )
                ),
                LottoNumber.valueOf(45)
        );
        assertThat(winningNumbers.match(lottoTicket)).isEqualTo(Grade.THREE);
    }
}
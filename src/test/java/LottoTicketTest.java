import domain.LottoBall;
import domain.LottoTicket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTicketTest {
    @Test
    void 하나의_로또티켓은_6개의_로또번호를_갖는다() {
        //given
        List<LottoBall> lottoBalls = generateLottoBalls(6);

        // when & then
        assertDoesNotThrow(() -> new LottoTicket(lottoBalls));
    }

    private List<LottoBall> generateLottoBalls(int count) {
        List<LottoBall> lottoBalls = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            lottoBalls.add(new LottoBall(i));
        }
        return lottoBalls;
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 7, 8})
    void 하나의_로또티켓은_6개의_로또번호를_갖는다_실패(int failLottoBallCount) {
        //given
        List<LottoBall> lottoBalls = generateLottoBalls(failLottoBallCount);

        // when & then
        assertThatCode(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

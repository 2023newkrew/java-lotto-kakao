package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGeneratorTest {
    @DisplayName("")
    @Test
    void generate() {
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator();
        LottoTicketGenerator.LottoBallGenerator lottoBallGenerator = new LottoTicketGenerator.LottoBallGenerator() {
            int index = 0;

            @Override
            public LottoBall generateBall() {
                index += 1;
                return new LottoBall(index);
            }
        };
        LottoTicket lottoTicket = lottoTicketGenerator.generate(lottoBallGenerator);
        Assertions.assertEquals(lottoTicket, new LottoTicket(new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ))));
    }
}

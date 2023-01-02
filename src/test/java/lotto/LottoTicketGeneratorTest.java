package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketGeneratorTest {
    @DisplayName("로또 생성 확인")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "'1,2,3,4,5,6'",
    })
    void generate(String rawBalls) {
        List<Integer> balls = Arrays.stream(rawBalls.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketGenerator();
        LottoTicket lottoTicket = lottoTicketGenerator.generate(new MockBallGenerator(balls));

        assertThat(lottoTicket)
                .isEqualTo(
                        new LottoTicket(balls.stream()
                                .map(LottoBall::new)
                                .collect(Collectors.toList()))
                )
        ;
    }

    private static class MockBallGenerator implements LottoTicketGenerator.LottoBallGenerator {
        private final LinkedList<Integer> expectedResult;

        private MockBallGenerator(Collection<Integer> expectedResult) {
            this.expectedResult = new LinkedList<>(expectedResult);
        }

        @Override
        public LottoBall generateBall() {
            Integer result = expectedResult.poll();
            if (Objects.isNull(result)) {
                throw new RuntimeException("더이상 꺼낼 값이 없습니다.");
            }
            return new LottoBall(result);
        }
    }
}

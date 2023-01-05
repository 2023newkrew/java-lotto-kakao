package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.domain.Ranking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutputViewTest {
    private final PrintStream standardOut = System.out;
    private final OutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("로또 출력")
    void printLottoTickets() {
        List<LottoTicket> lottoTickets = List.of(
                new LottoTicket(List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                ))
        );

        OutputView outputView = new OutputView();
        outputView.printLottoTickets(lottoTickets, 1);

        String output = "수동으로 1장, 자동으로 0개를 구매했습니다.\\n" +
                "\\[1, 2, 3, 4, 5, 6\\]\\n";
        assertThat(out.toString()).matches(output);
    }

    @Test
    @DisplayName("로또 당첨 결과 출력")
    void printMatchResult() {
        Map<Ranking, Long> rankingCount = Map.of(
                Ranking.FIFTH, 1L,
                Ranking.OTHER, 3L
        );
        MatchResult matchResult = new MatchResult(rankingCount);

        OutputView outputView = new OutputView();
        outputView.printMatchResult(matchResult);

        String output = "당첨 통계\\n" +
                "----------\\n" +
                "3개 일치 \\(5000원\\)- 1개\\n" +
                "4개 일치 \\(50000원\\)- 0개\\n" +
                "5개 일치 \\(1500000원\\)- 0개\\n" +
                "5개 일치, 보너스 볼 일치 \\(30000000원\\)- 0개\\n" +
                "6개 일치 \\(2000000000원\\)- 0개\\n" +
                "총 수익률은 1.25[0]*입니다.\\n";
        assertThat(out.toString()).matches(output);
    }
}
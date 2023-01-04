package lotto;

import lotto.model.Lotto;
import lotto.model.LottoList;
import lotto.model.LottoStatistics;
import lotto.model.WinningNumbers;
import lotto.model.enums.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoStatisticsTest {

    private LottoList lottoList;

    @BeforeEach
    void setUp() {
        List<Lotto> manyLotto = List.of(
                Lotto.of(12, 27, 29, 36, 37, 40),
                Lotto.of(14, 35, 37, 41, 42, 44),
                Lotto.of(1, 13, 16, 25, 40, 42),
                Lotto.of(16, 19, 24, 30, 35, 44),
                Lotto.of(2, 3, 6, 20, 26, 40),
                Lotto.of(12, 19, 24, 29, 30, 37),
                Lotto.of(20, 22, 24, 26, 35, 42),
                Lotto.of(4, 7, 18, 30, 38, 44),
                Lotto.of(3, 7, 13, 14, 30, 35)
        );
        lottoList = new LottoList(manyLotto);
    }

    @Test
    @DisplayName("당첨 번호에 따른 당첨 통계 테스트")
    void test_hard_coded_lotto_result() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 26);
        LottoStatistics lottoStatistics = new LottoStatistics();
        for (int i = 0; i < lottoList.length(); i++) {
            lottoStatistics.put(LottoResult.match(winningNumbers.check(lottoList.get(i))));
        }

        assertEquals(5000, lottoStatistics.getProfit());
    }
}

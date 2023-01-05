package lotto.domain;

import static lotto.domain.rank.LotteryRank.FIRST;
import static lotto.domain.rank.LotteryRank.SECOND;
import static lotto.domain.rank.LotteryRank.THIRD;
import static lotto.domain.rank.LotteryRank.FOURTH;
import static lotto.domain.rank.LotteryRank.FIFTH;
import static lotto.domain.rank.LotteryRank.DEFAULT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import lotto.domain.lotterynumber.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LotteryCheckerTest {
    LotteryChecker checker;

    @BeforeEach
    void initialize() {
        WinningNumbers winningNumbers;
        winningNumbers = new WinningNumbers(new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 6)), 7);
        checker = new LotteryChecker(winningNumbers);
    }

    @DisplayName("로또 등수를 정확하게 판단한다.")
    @Test
    void LotteryCheckerSingleCheckTest() {
        assertAll(
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 6))), FIRST),
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 7))), SECOND),
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 8))), THIRD),
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 3, 4, 8, 9))), FOURTH),
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 3, 8, 9, 10))), FIFTH),
                () -> assertEquals(checker.check(new LotteryNumberCombination(List.of(1, 2, 8, 9, 10, 11))), DEFAULT)
        );
    }

    @DisplayName("여러 개의 로또 입력에 대해 로또 등수 리스트를 반환한다.")
    @Test
    void LotteryCheckerMultipleCheckTest() {
        List<LotteryNumberCombination> lotteryTicket = new ArrayList<>(List.of(
                new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 6)), //First
                new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 7)), //Second
                new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 7))) //Second
        );

        assertEquals(checker.checkAll(lotteryTicket), List.of(1, 2, 0, 0, 0, 0));
    }
}

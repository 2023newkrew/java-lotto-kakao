package lotto.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrizeJudgeTest {
    @Test
    @DisplayName("can judge first prize")
    void can_judge_first_prize() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 3, 4, 5, 6)));
        assertEquals(Prize.FIRST, prize);
    }

    @Test
    @DisplayName("can judge second prize")
    void can_judge_second_prize() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 3, 4, 5, 7)));
        assertEquals(Prize.SECOND, prize);
    }

    @Test
    @DisplayName("can judge third prize")
    void can_judge_third_prize() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 3, 4, 5, 8)));
        assertEquals(Prize.THIRD, prize);
    }

    @Test
    @DisplayName("can judge fourth prize")
    void can_judge_fourth_prize() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 3, 4, 7, 8)));
        assertEquals(Prize.FOURTH, prize);
    }

    @Test
    @DisplayName("can judge fifth prize")
    void can_judge_fifth_prize() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 3, 7, 8, 9)));
        assertEquals(Prize.FIFTH, prize);
    }

    @Test
    @DisplayName("can judge losing")
    void can_judge_losing() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        PrizeJudge prizeJudge = new PrizeJudge(winningNumbers);

        Prize prize = prizeJudge.getPrizeOf(new Ticket(List.of(1, 2, 7, 8, 9, 10)));
        assertEquals(Prize.LOSING, prize);
    }
}

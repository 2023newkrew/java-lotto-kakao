package lotto.model;

public class PrizeJudge {
    private final WinningNumbers winningNumbers;

    public PrizeJudge(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Prize getPrizeOf(Ticket ticket) {
        return this.judge(
                this.winningNumbers.matchNumbers(ticket),
                this.winningNumbers.hasBonus(ticket));
    }

    private Prize judge(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return Prize.FIRST;
        if (matchCount == 5 && hasBonus) return Prize.SECOND;
        if (matchCount == 5) return Prize.THIRD;
        if (matchCount == 4) return Prize.FOURTH;
        if (matchCount == 3) return Prize.FIFTH;
        return Prize.LOSING;
    }
}

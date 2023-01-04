package lotto.model;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static boolean isValidWinningNumbers(List<Integer> winningNumbers) {
        return Ticket.isValidNumbers(winningNumbers);
    }

    public static boolean isValidBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        return Ticket.isValidNumber(bonusNumber)
                && !winningNumbers.contains(bonusNumber);
    }

    private boolean isMatched(int number) {
        return this.winningNumbers.contains(number);
    }

    private int count(int number) {
        if (this.isMatched(number)) {
            return 1;
        }
        return 0;
    }

    public int matchNumbers(Ticket ticket) {
        int matchCount = 0;
        for (int number : ticket.getNumbers()) {
            matchCount += this.count(number);
        }
        return matchCount;
    }

    public boolean hasBonus(Ticket ticket) {
        return ticket.getNumbers().contains(this.bonusNumber);
    }
}

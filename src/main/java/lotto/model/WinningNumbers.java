package lotto.model;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers.subList(0, winningNumbers.size() - 1);
        this.bonusNumber = winningNumbers.get(winningNumbers.size() - 1);
    }

    public Price getPrice(Lotto lotto) {
        return judgePrice(matchNumbers(lotto), hasBonus(lotto));
    }

    private Price judgePrice(int matchNumbers, boolean hasBonus) {
        if (matchNumbers == 6) {
            return Price.FIRST;
        }
        if (matchNumbers == 5 && hasBonus) {
            return Price.SECOND;
        }
        if (matchNumbers == 5) {
            return Price.THIRD;
        }
        if (matchNumbers == 4) {
            return Price.FOURTH;
        }
        if (matchNumbers == 3) {
            return Price.FIFTH;
        }
        return Price.NOTHING;
    }

    private boolean hasBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private int matchNumbers(Lotto lotto) {
        int count = 0;
        for (Integer number : lotto.getNumbers()) {
            count += returnValueIfContain(number);
        }

        return count;
    }

    private int returnValueIfContain(int number) {
        return winningNumbers.contains(number) ? 1 : 0;
    }
}

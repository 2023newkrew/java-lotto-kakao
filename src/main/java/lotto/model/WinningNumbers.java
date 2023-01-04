package lotto.model;

import java.util.Collections;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> winingMainNumbers;
    private final int winningBonusNumber;

    public WinningNumbers(List<Integer> winingMainNumbers, int winningBonusNumber) {
        this.winingMainNumbers = winingMainNumbers;
        this.winningBonusNumber = winningBonusNumber;
    }

    public List<Integer> getWiningMainNumbers() {
        return Collections.unmodifiableList(winingMainNumbers);
    }

    public int getWinningBonusNumber() {
        return winningBonusNumber;
    }
}

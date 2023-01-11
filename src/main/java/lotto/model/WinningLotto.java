package lotto.model;

import java.util.List;

public class WinningLotto extends Lotto {
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        super(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public Price getPrice(Lotto lotto) {
        return Price.judgePrice(matchNumbers(lotto), hasBonus(lotto));
    }


    private boolean hasBonus(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    private int matchNumbers(Lotto lotto) {
        int count = 0;
        for (LottoNumber number : lotto.getNumbers()) {
            count += returnValueIfContain(number.getNumber());
        }

        return count;
    }

    private int returnValueIfContain(int number) {
        return contains(number) ? 1 : 0;
    }
}

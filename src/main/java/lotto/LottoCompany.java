package lotto;

import java.util.List;

public class LottoCompany {

    private final List<Integer> winningNumbers;
    private final int bonus;

    public LottoCompany(List<Integer> winningNumbers, int bonus) {
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    public Prize judge(Lotto lotto) {
        long overlappedNumberCount = overlappedNumberCount(lotto);
        boolean hasBonus = lotto.hasBonus(bonus);
        return Prize.valueOf(overlappedNumberCount, hasBonus);
    }

    private long overlappedNumberCount(Lotto lotto) {
        return winningNumbers.stream()
                .filter(lotto::hasNumber)
                .count();
    }
}

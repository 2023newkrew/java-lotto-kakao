package lotto.model;

import lotto.model.enums.RankingType;
import lotto.utils.RandomNumbersGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto() {
        this(RandomNumbersGenerator.generateRandomNumbers());
    }

    public Lotto(List<Integer> lotto) {
        Collections.sort(lotto);
        this.lotto = lotto;
    }

    public List<Integer> getLotto() {
        return lotto;
    }

    public RankingType checkWin(WinningNumbers winningNumbers) {
        int matchedNum = lotto.stream()
                .filter(winningNumbers.getWiningMainNumbers()::contains)
                .collect(Collectors.toSet())
                .size();
        if (matchedNum == 5) {
            return RankingType.findRanking(matchedNum, checkBonus(winningNumbers.getWinningBonusNumber()));
        }
        return RankingType.findRanking(matchedNum, false);
    }

    private boolean checkBonus(int bonus) {
        return lotto.contains(bonus);
    }
}

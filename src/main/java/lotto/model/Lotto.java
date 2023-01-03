package lotto.model;

import lotto.utils.RandomNumbersGenerator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 로또 번호를 가지는 일급객체입니다.
 */
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
        List<Integer> lottoReadOnly = new ArrayList<>(lotto);   // 방어적 복사
        return Collections.unmodifiableList(lottoReadOnly);
    }

    public Ranking checkWin(List<Integer> winNumbers, int bonus) {
        int matchedNum = lotto.stream()
                .filter(winNumbers::contains)
                .collect(Collectors.toSet())
                .size();
        if(matchedNum == 5) {
            return Ranking.findRanking(matchedNum, checkBonus(bonus));
        }
        return Ranking.findRanking(matchedNum, false);
    }

    private boolean checkBonus(int bonus){
        return lotto.contains(bonus);
    }
}

package lotto.model;

import java.util.Collection;
import java.util.List;

public class LottoTrialManual extends LottoTrial {
    public LottoTrialManual(Collection<LottoBallNumber> ballNumbers) {
        super.ballNumbers.addAll(ballNumbers);

        super.check();
        super.sort();
    }

    public LottoTrialManual(List<Integer> ballNumbers) {
        for (int ball : ballNumbers){
            super.ballNumbers.add(new LottoBallNumber(ball));
        }

        super.check();
        super.sort();
    }

}

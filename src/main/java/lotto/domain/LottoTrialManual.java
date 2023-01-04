package lotto.domain;

import java.util.Collection;
import java.util.List;

public class LottoTrialManual extends LottoTrial{
    public LottoTrialManual(Collection<LottoBallNumber> ballNumbers){
        super.ballNumbers.addAll(ballNumbers);
        super.validate(this);
        super.sort(this);
    }

    public LottoTrialManual(List<Integer> ballNumbers){
        for (int ball:ballNumbers){
            super.ballNumbers.add(LottoBallNumber.get(ball));
        }
        super.validate(this);
        super.sort(this);
    }

}

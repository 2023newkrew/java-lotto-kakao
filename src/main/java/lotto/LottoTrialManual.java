package lotto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LottoTrialManual extends LottoTrial{
    public LottoTrialManual(Collection<LottoBallNumber> ballNumbers){
        ballNumbers.addAll(ballNumbers);
        check(this);
    }

    public LottoTrialManual(List<Integer> ballNumbers){
        for (int ball:ballNumbers){
            super.ballNumbers.add(new LottoBallNumber(ball));
        }
        check(this);
    }

}

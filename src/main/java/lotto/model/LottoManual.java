package lotto.model;

import java.util.Collection;
import java.util.List;

public class LottoManual extends Lotto {
    public LottoManual(Collection<LottoBall> ballNumbers) {
        super.balls.addAll(ballNumbers);

        super.check();
        super.sort();
    }

    public LottoManual(List<Integer> ballNumbers) {
        for (int ball : ballNumbers){
            super.balls.add(new LottoBall(ball));
        }

        super.check();
        super.sort();
    }
}

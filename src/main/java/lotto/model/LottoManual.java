package lotto.model;

import java.util.Collection;
import java.util.List;

public class LottoManual extends Lotto {
    public LottoManual(Collection<LottoBall> balls) {
        super.balls.addAll(balls);

        super.check();
        super.sort();
    }

    public LottoManual(List<Integer> balls) {
        for (int ball : balls){
            super.balls.add(new LottoBall(ball));
        }

        super.check();
        super.sort();
    }
}

package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPickerRandom implements LottoPicker {
    private final List<LottoBall> allBalls = new ArrayList<>();

    public LottoPickerRandom() {
        for (int i = LottoConstants.BALLNUMBER_MIN_VALUE; i <= LottoConstants.BALLNUMBER_MAX_VALUE; i++){
            allBalls.add(new LottoBall(i));
        }

        Collections.shuffle(allBalls);
    }

    @Override
    public List<LottoBall> pick() {
        List<LottoBall> result = new ArrayList<>();

        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT; i++) {
            result.add(allBalls.get(i));
        }

        return result;
    }
}

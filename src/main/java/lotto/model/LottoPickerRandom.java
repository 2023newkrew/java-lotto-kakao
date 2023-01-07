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
    public LottoBall pick() {
        LottoBall result = allBalls.get(0);
        allBalls.remove(0);

        return result;
    }
}

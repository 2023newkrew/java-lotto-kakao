package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPickerRandom implements LottoPicker {
    private final List<LottoBall> picker = new ArrayList<>();

    public LottoPickerRandom() {
        for (int i = LottoConstants.BALLNUMBER_MIN_VALUE; i <= LottoConstants.BALLNUMBER_MAX_VALUE; i++){
            picker.add(new LottoBall(i));
        }

        Collections.shuffle(picker);
    }

    @Override
    public LottoBall pickOne() {
        LottoBall result = picker.get(0);
        picker.remove(0);

        return result;
    }
}

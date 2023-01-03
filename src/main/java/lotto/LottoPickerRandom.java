package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPickerRandom implements LottoPicker{
    private final List<LottoBallNumber> picker = new ArrayList<>();
    public LottoPickerRandom(){
        for (int i=LottoConstants.BALLNUMBER_MIN_VALUE;i<=LottoConstants.BALLNUMBER_MAX_VALUE;i++){
            picker.add(new LottoBallNumber(i));
        }
        Collections.shuffle(picker);
    }

    @Override
    public LottoBallNumber pickOne(){
        LottoBallNumber result = picker.get(0);
        picker.remove(0);
        return result;
    }
}

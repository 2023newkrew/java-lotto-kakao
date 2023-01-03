package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPickerRandom implements LottoPicker{
    private final List<LottoBallNumber> picker = new ArrayList<>();
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    public LottoPickerRandom(){
        for (int i=MIN_VALUE;i<=MAX_VALUE;i++){
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

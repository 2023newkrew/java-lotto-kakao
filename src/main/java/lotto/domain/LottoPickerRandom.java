package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static lotto.domain.constants.LottoConstants.*;


public class LottoPickerRandom implements LottoPicker{
    private final List<LottoBallNumber> picker = new ArrayList<>();
    /**
     * put picker all available LottoBallNumber and shuffle it.
     */
    public LottoPickerRandom(){
        for (int i=BALLNUMBER_MIN_VALUE;i<=BALLNUMBER_MAX_VALUE;i++){
            picker.add(LottoBallNumber.get(i));
        }
        Collections.shuffle(picker);
    }

    /**
     * get random unpicked LottoBallNumber, and remove it.
     * @throws IndexOutOfBoundsException if picker does not have any LottoBallNumber
     * @return random unpicked LottoBallNumber
     */
    @Override
    public LottoBallNumber pickOne(){
        LottoBallNumber result = picker.get(0);
        picker.remove(0);
        return result;
    }
}

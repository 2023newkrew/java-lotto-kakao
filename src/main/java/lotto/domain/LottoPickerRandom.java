package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static lotto.domain.constants.LottoConstants.*;


/**
 * LottoPickerRandom is the LottoPicker that picks random LottoBallNumber.
 * Once you picked, the number you picked from pickOne() would not be picked twice unless you make new LottoPicker instance.
 * @author daniel.tomi
 */
public class LottoPickerRandom implements LottoPicker{
    private final List<LottoBallNumber> picker = new ArrayList<>();
    public LottoPickerRandom(){
        for (int i=BALLNUMBER_MIN_VALUE;i<=BALLNUMBER_MAX_VALUE;i++){
            picker.add(LottoBallNumber.get(i));
        }
        Collections.shuffle(picker);
    }

    /**
     * Returns random unpicked LottoBallNumber, with removing it.
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

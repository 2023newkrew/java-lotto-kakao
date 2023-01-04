package lotto.domain;
import static lotto.domain.LottoConstants.*;

public class LottoTrialRandom extends LottoTrial {
    private final LottoPicker picker;
    public LottoTrialRandom(LottoPicker lottoPicker){
        this.picker = lottoPicker;
        for (int i=1;i<=BALLCOUNT_LIMIT;i++){
            super.ballNumbers.add(picker.pickOne());
        }
        super.validate(this);
        super.sort(this);
    }
}

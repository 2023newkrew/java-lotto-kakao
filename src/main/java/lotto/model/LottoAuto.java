package lotto.model;

public class LottoAuto extends Lotto {
    public LottoAuto(LottoPicker lottoPicker) {
        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT; i++){
            super.balls.add(lottoPicker.pickOne());
        }

        super.check();
        super.sort();
    }
}

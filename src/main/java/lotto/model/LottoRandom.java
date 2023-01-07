package lotto.model;

public class LottoRandom extends Lotto {
    public LottoRandom(LottoPicker lottoPicker) {
        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT; i++){
            super.balls.add(lottoPicker.pickOne());
        }

        super.check();
        super.sort();
    }
}

package lotto.model;

public class LottoTrialRandom extends LottoTrial {
    public LottoTrialRandom(LottoPicker lottoPicker) {
        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT; i++){
            super.ballNumbers.add(lottoPicker.pickOne());
        }

        super.check();
        super.sort();
    }
}

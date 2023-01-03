package lotto;

public class LottoTrialRandom extends LottoTrial {
    private LottoPicker picker;
    public LottoTrialRandom(LottoPicker lottoPicker){
        this.picker = lottoPicker;
        for (int i=1;i<=LottoConstants.BALLCOUNT_LIMIT;i++){
            super.ballNumbers.add(picker.pickOne());
        }
        super.check(this);
        super.sort(this);
    }
}

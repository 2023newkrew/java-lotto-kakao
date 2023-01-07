package lotto.model;

public class LottoAuto extends Lotto {
    public LottoAuto(LottoPicker lottoPicker) {
        super.balls.addAll(lottoPicker.pick());

        super.check();
        super.sort();
    }
}

package javalotto;

import javalotto.controller.LottoController;
import javalotto.domain.LottoGenerator;
import javalotto.domain.RandomNumberGenerator;
import javalotto.view.InputView;
import javalotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = LottoController.of(
                InputView.newInstance(),
                OutputView.newInstance(),
                LottoGenerator.from(RandomNumberGenerator.newInstance())
        );
try {
    lottoController.simulate();

} catch (Exception e) {
    e.printStackTrace();
}
    }
}

package lotto;

import lotto.domain.LottoGenerator;
import lotto.view.LottoView;

public class Application {

    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoView lottoView = new LottoView();
        LottoController lottoController = new LottoController(lottoView, lottoGenerator);
        lottoController.start();
    }
}

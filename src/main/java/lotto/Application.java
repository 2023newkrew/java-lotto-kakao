package lotto;


import lotto.controller.LottoSimulator;
import lotto.model.store.Money;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class Application {

    public static void main(String[] args) {
        Money lottoPrice = Money.valueOf(1000L);
        LottoInputView inputView = new LottoInputView();
        LottoOutputView outputView = new LottoOutputView();
        LottoSimulator simulator = LottoSimulator.create(lottoPrice, inputView, outputView);
        simulator.run();
    }
}

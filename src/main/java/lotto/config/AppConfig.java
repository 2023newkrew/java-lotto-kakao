package lotto.config;

import lotto.domain.GeneratePolicy;
import lotto.domain.LottoHandler;
import lotto.domain.RandomGeneratePolicy;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.View;

public class AppConfig {

    private static final AppConfig instance = new AppConfig();

    private AppConfig() {
    }


    public static AppConfig getInstance() {
        return instance;
    }

    private static final GeneratePolicy generatePolicy = new RandomGeneratePolicy();
    private static final View view = View.createView(new InputView(), new OutputView());

    private static final LottoHandler lottoHandler = LottoHandler.createLottoHandler();

    public GeneratePolicy getGeneratePolicy() {
        return generatePolicy;
    }

    public LottoHandler getLottoHandler() {
        return lottoHandler;
    }

    public View getView() {
        return view;

    }
}



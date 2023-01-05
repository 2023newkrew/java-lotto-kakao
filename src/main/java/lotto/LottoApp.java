package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class LottoApp {

    private final AppConfig appConfig;

    public LottoApp(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void run() {
        LottoController.createLottoController(AppConfig.getView()).play();
    }

    public static void main(String[] args) {
        new LottoApp(AppConfig.getInstance()).run();


    }
}

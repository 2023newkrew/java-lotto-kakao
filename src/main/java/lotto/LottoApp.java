package lotto;

import lotto.controller.LottoController;

public class LottoApp {

    private final AppConfig appConfig;

    public LottoApp(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void run() {
        appConfig.getLottoController().run();

    }

    public static void main(String[] args) {
        new LottoApp(new AppConfig()).run();
    }
}

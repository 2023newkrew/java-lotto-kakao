package lotto.domain;

import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenserSetting {
    private final LottoSetting lottoSetting;
    private NumberSelectStrategy numberSelectStrategy;

    public LottoDispenserSetting(LottoSetting lottoSetting,
            NumberSelectStrategy numberSelectStrategy) {
        this.lottoSetting = lottoSetting;
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public LottoSetting getLottoSetting() {
        return lottoSetting;
    }

    public NumberSelectStrategy getNumberSelectStrategy() {
        return numberSelectStrategy;
    }

    public void setNumberSelectStrategy(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }
}

package lotto.domain;

import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenserSetting {
    private final LottoSetting lottoSetting;
    private final NumberSelectStrategy numberSelectStrategy;

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
}

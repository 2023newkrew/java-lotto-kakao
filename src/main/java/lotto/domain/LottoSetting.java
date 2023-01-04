package lotto.domain;

import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.strategy.RandomNumberSelectStrategy;

public class LottoSetting {

    private int lottoTicketPrice = 1000;
    private NumberSelectStrategy numberSelectStrategy = RandomNumberSelectStrategy.getInstance();

    public int getLottoTicketPrice() {
        return lottoTicketPrice;
    }

    public void setLottoTicketPrice(int lottoTicketPrice) {
        this.lottoTicketPrice = lottoTicketPrice;
    }

    public NumberSelectStrategy getNumberSelectStrategy() {
        return numberSelectStrategy;
    }

    public void setNumberSelectStrategy(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }
}

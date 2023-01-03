package lotto.service;

import lotto.domain.LottoTicket;

public class LottoCalculator {

    private final LottoTicket winTicket;

    public LottoCalculator(LottoTicket winTicket) {
        this.winTicket = winTicket;
    }

    public int checkSameCount(LottoTicket userTicket) {
        return 1;
    }
}
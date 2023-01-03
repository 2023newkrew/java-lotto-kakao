package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

public class LottoCalculator {

    private final LottoWinnerTicket winTicket;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
    }

    public int checkSameCount(LottoTicket userTicket) {
        int sameCount = 0;
        for(int number : userTicket.getLottoNumbers()){
            sameCount += checkContains(number);
        }
        return sameCount;
    }

    private int checkContains(int number){
        if(winTicket.getLottoNumbers().contains(number)) return 1;
        return 0;
    }

    public boolean calcBonusNumber(LottoTicket lottoTicket) {
        return true;
    }
}
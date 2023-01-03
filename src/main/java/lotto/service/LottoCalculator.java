package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;

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


    public boolean calcBonusNumber(LottoTicket userTicket) {
        return userTicket.getLottoNumbers().contains(winTicket.getBonusNumber());
    }


    private int checkContains(int number){
        if(winTicket.getLottoNumbers().contains(number)) return 1;
        return 0;
    }

    public long getWinAmount() {
        return 0;
    }
}
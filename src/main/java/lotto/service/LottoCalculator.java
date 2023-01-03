package lotto.service;

import lotto.domain.LottoTicket;

public class LottoCalculator {

    private final LottoTicket winTicket;

    public LottoCalculator(LottoTicket winTicket) {
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
}
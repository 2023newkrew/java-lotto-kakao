package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.List;

public class LottoCalculator {

    private final LottoWinnerTicket winTicket;
    private final ArrayList<Integer> winValue;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        this.winValue = new ArrayList<>(List.of(5000, 50000, 1500000, 30000000, 2000000000));
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

    public long getWinAmount(ArrayList<Integer> winScore) {
        long summary = 0;
        for (int i=0; i < winScore.size(); i++) {
            summary += (long) winScore.get(i) * this.winValue.get(i);
        }
        return summary;
    }

    public double calcRateOfReturn(int amount, long summary) {
        return 0.0;
    }
}
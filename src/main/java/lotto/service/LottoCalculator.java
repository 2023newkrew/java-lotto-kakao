package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoCalculator {
    private static final Integer MIN_WIN_NUM = 3;
    private final LottoWinnerTicket winTicket;
    private final ArrayList<Integer> winValue;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        this.winValue = new ArrayList<>(Arrays.asList(5000, 50000, 1500000, 2000000000, 30000000));
    }

    public ArrayList<Integer> getWinScore(LottoTickets lottoTickets) {
        ArrayList<LottoTicket> tickets = lottoTickets.getTickets();

        ArrayList<Integer> winScore = new ArrayList<>(Collections.nCopies(5,0));
        for (LottoTicket ticket : tickets) {
            int index = getScore(ticket);
            if (index != -1){
                int value = winScore.get(index);
                winScore.set(index, value + 1);
            }
        }
        return winScore;
    }

    public long getWinSummary(ArrayList<Integer> score) {
        long summary = 0;
        for (int i=0; i < score.size(); i++) {
            summary += (long) score.get(i) * this.winValue.get(i);
        }
        return summary;
    }

    public double calcRateOfReturn(ArrayList<Integer> winScore, int amount) {
        double summary = getWinSummary(winScore);
        amount -= amount % MIN_PURCHASE_PRICE;
        return summary / amount;
    }

    private int getScore (LottoTicket ticket) {
        int sameCount = checkSameCount(ticket);
        if(sameCount < MIN_WIN_NUM) return -1;
        sameCount -= MIN_WIN_NUM;
        if(sameCount == 5 - MIN_WIN_NUM && isBonusNumber(ticket)) {
            return 4;
        }
        return sameCount;
    }

    public int checkSameCount(LottoTicket userTicket) {
        int sameCount = 0;
        for(int number : userTicket.getLottoNumbers()){
            sameCount += checkContains(number);
        }
        return sameCount;
    }

    public boolean isBonusNumber(LottoTicket userTicket) {
        return userTicket.getLottoNumbers().contains(winTicket.getBonusNumber());
    }

    private int checkContains(int number){
        if(winTicket.getLottoNumbers().contains(number)) return 1;
        return 0;
    }

}
package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoCalculator {
    private static final Integer MIN_WIN_NUM = 3;
    private final LottoWinnerTicket winTicket;
    private final ArrayList<Integer> winValue;
    private final ArrayList<Integer> winScore;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        this.winValue = new ArrayList<>(Arrays.asList(5000, 50000, 1500000, 2000000000, 30000000));
        this.winScore = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    }

    public double calcRateOfReturn(int amount) {
        double summary = getWinSummary(this.winScore);
        amount -= amount % 1000;
        return summary / amount;
    }

    public ArrayList<Integer> getResult(LottoTickets lottoTickets) {
        ArrayList<LottoTicket> tickets = lottoTickets.getTickets();
        for (LottoTicket ticket : tickets) {
            getScore(ticket);
        }
        return this.winScore;
    }

    public long getWinSummary(ArrayList<Integer> score) {
        long summary = 0;
        for (int i=0; i < score.size(); i++) {
            summary += (long) score.get(i) * this.winValue.get(i);
        }
        return summary;
    }

    public void getScore (LottoTicket ticket) {
        int sameCount = checkSameCount(ticket);
        if(sameCount < MIN_WIN_NUM) return;
        sameCount -= MIN_WIN_NUM;
        if(sameCount == 5 - MIN_WIN_NUM && isBonusNumber(ticket)) {
            this.winScore.set(4, this.winScore.get(sameCount) + 1);
            return;
        }
        this.winScore.set(sameCount, this.winScore.get(sameCount) + 1);
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
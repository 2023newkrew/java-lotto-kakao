package lotto.service;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoCalculator {
    private final LottoWinnerTicket winTicket;
    private final List<Integer> winValue;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        Integer[] valueList = {5000, 50000, 1500000, 2000000000, 30000000};
        this.winValue = new ArrayList<>(List.of(valueList));
    }

    public WinnerScore getWinScore(LottoTickets lottoTickets) {
        WinnerScore winnerScore = new WinnerScore();
        winnerScore.addScore(lottoTickets, this.winTicket);
        return winnerScore;
    }

    public double calcRateOfReturn(WinnerScore winScore, int totalAmount) {
        int purchaseAmount = totalAmount - totalAmount % MIN_PURCHASE_PRICE;
        double summary = getWinSummary(winScore);
        return summary / purchaseAmount;
    }

    public long getWinSummary(WinnerScore score) {
        long summary = 0;
        for (int i=0; i < score.getWinnerScore().size(); i++) {
            summary += (long) score.getWinnerScore().get(i) * this.winValue.get(i);
        }
        return summary;
    }
}
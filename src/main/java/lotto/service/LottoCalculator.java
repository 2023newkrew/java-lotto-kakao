package lotto.service;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoCalculator {
    private static final Integer MIN_WIN_NUM = 3;
    private final LottoWinnerTicket winTicket;
    private final List<Integer> winValue;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        Integer[] valueList = {5000, 50000, 1500000, 2000000000, 30000000};
        this.winValue = new ArrayList<>(List.of(valueList));
    }

    public WinnerScore getWinScore(LottoTickets lottoTickets) {
        List<LottoTicket> tickets = lottoTickets.getTickets();
        WinnerScore winnerScore = new WinnerScore();
        for (LottoTicket ticket : tickets) {
            int index = getScore(ticket);
            if (index != -1){
                winnerScore.addScore(index);
            }
        }
        return winnerScore;
    }

    public long getWinSummary(WinnerScore score) {
        long summary = 0;
        for (int i=0; i < score.getWinnerScore().size(); i++) {
            summary += (long) score.getWinnerScore().get(i) * this.winValue.get(i);
        }
        return summary;
    }

    public double calcRateOfReturn(WinnerScore winScore, int amount) {
        double summary = getWinSummary(winScore);
        amount -= amount % MIN_PURCHASE_PRICE;
        return summary / amount;
    }

    public int checkSameCount(LottoTicket userTicket) {
        int sameCount = 0;
        for (LottoNumber number : userTicket.getLottoNumbers()){
            sameCount += checkContains(number);
        }
        return sameCount;
    }

    public boolean isBonusNumber(LottoTicket userTicket) {
        return userTicket.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
                .contains(winTicket.getBonusNumber().getNumber());
    }

    private int getScore (LottoTicket ticket) {
        int sameCount = checkSameCount(ticket);
        if (sameCount < MIN_WIN_NUM) return -1;
        sameCount -= MIN_WIN_NUM;
        if (sameCount == 5 - MIN_WIN_NUM && isBonusNumber(ticket)) {
            return 4;
        }
        return sameCount;
    }

    private int checkContains(LottoNumber number){
        if (winTicket.getLottoNumbers().stream()
                    .map(LottoNumber::getNumber)
                    .collect(Collectors.toList())
                    .contains(number.getNumber())) {
            return 1;
        }
        return 0;
    }
}
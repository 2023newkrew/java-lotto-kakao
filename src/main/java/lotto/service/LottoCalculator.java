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
    private ArrayList<Integer> winScore;

    public LottoCalculator(LottoWinnerTicket winTicket) {
        this.winTicket = winTicket;
        this.winValue = new ArrayList<>(Arrays.asList(5000, 50000, 1500000, 2000000000, 30000000));
        this.winScore = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    }

    // 하나의 로또 티켓 중 몇 개의 변호를 맞췄는지
    public int checkSameCount(LottoTicket userTicket) {
        int sameCount = 0;
        for(int number : userTicket.getLottoNumbers()){
            sameCount += checkContains(number);
        }
        return sameCount;
    }

    // 보너스 숫자와 일치하는 것이 있는지 확인
    public boolean isBonusNumber(LottoTicket userTicket) {
        return userTicket.getLottoNumbers().contains(winTicket.getBonusNumber());
    }

    // 로또 티켓에 번호를 포함하고 있는지
    private int checkContains(int number){
        if(winTicket.getLottoNumbers().contains(number)) return 1;
        return 0;
    }

    // 수익률 계산
    public double calcRateOfReturn(int amount) {
        long summary = getWinSummary(this.winScore);
        amount -= amount % 1000;
        return summary / amount;
    }

    // 로또를 통해 얻은 총 수입 계산
    public long getWinSummary(ArrayList<Integer> score) {
        long summary = 0;
        for (int i=0; i < score.size(); i++) {
            summary += (long) score.get(i) * this.winValue.get(i);
        }
        return summary;
    }

    // 로또 통계 배열 생성
    public ArrayList<Integer> getResult(LottoTickets lottoTickets) {
        ArrayList<LottoTicket> tickets = lottoTickets.getTickets();
        // result
        for (LottoTicket ticket : tickets) {
            getScore(ticket);
        }
        return this.winScore;
    }

    // 당첨 통계 배열 계산
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
}
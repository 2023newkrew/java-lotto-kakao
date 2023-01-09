package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.utils.Constants.MIN_WIN_NUM;


public class WinnerScore {
    private final List<Integer> winnerScore;

    public WinnerScore() {
        this.winnerScore = new ArrayList<>(Collections.nCopies(5, 0));
    }

    public void addScore(LottoTickets lottoTickets, LottoWinnerTicket winnerTicket){
        List<LottoTicket> tickets = lottoTickets.getTickets();
        for (LottoTicket ticket : tickets) {
            int index = getScore(ticket, winnerTicket);
            if (index != -1){
                Integer value = this.winnerScore.get(index);
                this.winnerScore.set(index, value + 1);
            }
        }
    }

    public List<Integer> getWinnerScore(){
        return this.winnerScore;
    }

    public boolean isBonusNumber(LottoTicket userTicket, LottoWinnerTicket winnerTicket) {
        return userTicket.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
                .contains(winnerTicket.getBonusNumber().getNumber());
    }

    public int checkSameCount(LottoTicket userTicket, LottoWinnerTicket winnerTicket) {
        int sameCount = 0;
        for (LottoNumber number : userTicket.getLottoNumbers()){
            sameCount += checkContains(number, winnerTicket);
        }
        return sameCount;
    }

    private int getScore (LottoTicket ticket, LottoWinnerTicket winnerTicket) {
        int sameCount = checkSameCount(ticket, winnerTicket);
        if (sameCount < MIN_WIN_NUM) return -1;
        sameCount -= MIN_WIN_NUM;
        if (sameCount == 5 - MIN_WIN_NUM && isBonusNumber(ticket, winnerTicket)) {
            return 4;
        }
        return sameCount;
    }

    private int checkContains(LottoNumber number, LottoWinnerTicket winnerTicket){
        if (winnerTicket.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
                .contains(number.getNumber())) {
            return 1;
        }
        return 0;
    }
}

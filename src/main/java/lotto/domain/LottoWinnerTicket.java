package lotto.domain;


import java.util.ArrayList;
import java.util.Collection;

public class LottoWinnerTicket {
    private final LottoTicket lottoTicket;
    private final int bonusBall;


    public LottoWinnerTicket(LottoTicket lottoTicket, int bonusBall) {
        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }


    public ArrayList<Integer> getLottoNumbers() {
        return lottoTicket.getLottoNumbers();
    }

    public Integer getBonusNumber() {
        return bonusBall;
    }
}

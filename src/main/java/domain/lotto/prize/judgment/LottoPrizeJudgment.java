package domain.lotto.prize.judgment;

import domain.lotto.LottoWinningNumber;
import domain.lotto.ticket.LottoTicket;

public interface LottoPrizeJudgment {

    boolean judge(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber);
}

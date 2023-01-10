package lotto.domain.lotto.prize.judgment;

import lotto.domain.lotto.LottoWinningNumber;
import lotto.domain.lotto.ticket.LottoTicket;

public interface LottoPrizeJudgment {

    boolean judge(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber);
}

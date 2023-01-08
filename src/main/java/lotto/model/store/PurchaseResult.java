package lotto.model.store;

import lotto.model.ranking.AnalysisResult;
import lotto.model.ranking.RankingCounts;
import lotto.model.ranking.WinningNumbers;
import lotto.model.ticket.LottoTicket;

import java.util.Objects;

public class PurchaseResult {

    private final LottoTicket ticket;

    private final LottoReceipt receipt;

    public static PurchaseResult of(LottoTicket ticket, LottoReceipt receipt) {
        if (Objects.isNull(ticket)) {
            throw new IllegalArgumentException("로또 티켓이 없습니다.");
        }
        if (Objects.isNull(receipt)) {
            throw new IllegalArgumentException("영수증이 없습니다.");
        }

        return new PurchaseResult(ticket, receipt);
    }

    private PurchaseResult(LottoTicket ticket, LottoReceipt receipt) {
        this.ticket = ticket;
        this.receipt = receipt;
    }

    public LottoTicket getTicket() {
        return ticket;
    }

    public LottoReceipt getReceipt() {
        return receipt;
    }

    public AnalysisResult analyze(WinningNumbers winningNumbers) {
        RankingCounts rankingCounts = ticket.judge(winningNumbers);

        return AnalysisResult.from(rankingCounts, receipt);
    }
}

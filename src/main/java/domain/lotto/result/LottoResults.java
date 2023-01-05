package domain.lotto.result;

import domain.lotto.number.WinningNumbers;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResults {
    private final Integer LOTTO_TICKET_PRICE = 1_000;

    private static final Map<LottoResultType, Integer> lottoResultCount = new HashMap<>();

    public LottoResults(final LottoTickets lottoTickets, final WinningNumbers winningNumbers) {
        Arrays.stream(LottoResultType.values()).
                forEach(lottoResultType -> lottoResultCount.put(lottoResultType, 0));
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            countResult(LottoChecker.getResult(lottoTicket, winningNumbers));
        }
    }

    public Integer getResultCount(final LottoResultType lottoResultType) {
        return lottoResultCount.getOrDefault(lottoResultType, 0);
    }

    private void countResult(final LottoResultType resultType) {
        lottoResultCount.put(resultType, lottoResultCount.getOrDefault(resultType, 0) + 1);
    }

    public float getProfitRate() {
        int ticketNumber = lottoResultCount.values().stream().reduce((sum, num) -> sum + num).get();
        int budget = ticketNumber * LOTTO_TICKET_PRICE;
        int profit = lottoResultCount.keySet().stream()
                .mapToInt((key) -> lottoResultCount.get(key) * key.getPrize())
                .sum();
        return Math.round(((float) profit / budget)*100.0)/100.0f;
    }
}

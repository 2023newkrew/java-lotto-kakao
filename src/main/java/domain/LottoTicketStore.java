package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketStore {
    public static final int MANUAL_LOTTO_TICKET_COST = 1_000;
    public static final int AUTO_LOTTO_TICKET_COST = 1_000;

    private final LottoTicketGenerator lottoTicketGenerator;

    public LottoTicketStore(LottoTicketGenerator lottoTicketGenerator){
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public List<LottoNumbers> purchaseManualLotto(List<List<LottoNumber>> lottoNumbers, int paymentCost) {
        int totalCost = lottoNumbers.size() * MANUAL_LOTTO_TICKET_COST;

        validationCost(paymentCost, totalCost);

        return lottoNumbers.stream()
                .map(LottoNumbers::new)
                .collect(Collectors.toList());
    }

    public List<LottoNumbers> purchaseAutoLotto(int purchaseLottoCount, int paymentCost) {
        int totalCost = purchaseLottoCount * AUTO_LOTTO_TICKET_COST;

        validationCost(paymentCost, totalCost);
        return lottoTicketGenerator.generate(purchaseLottoCount);
    }

    private static void validationCost(int paymentCost, int totalCost) {
        if(totalCost > paymentCost){
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
    }
}

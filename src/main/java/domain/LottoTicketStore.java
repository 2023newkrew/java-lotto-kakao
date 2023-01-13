package domain;

import java.util.List;

public class LottoTicketStore {
    public class LottoTicket{
        private final LottoNumbers lottoNumbers;
        private LottoTicket(LottoNumbers lottoNumbers){
            this.lottoNumbers = lottoNumbers;
        }

        public LottoNumbers getLottoNumbers() {
            return lottoNumbers;
        }
    }

    public static final int MANUAL_LOTTO_TICKET_COST = 1_000;
    public static final int AUTO_LOTTO_TICKET_COST = 1_000;

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoTicketStore(LottoNumbersGenerator lottoNumbersGenerator){
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public List<LottoTicket> purchaseManualLotto(List<LottoNumbers> lottoNumbers, int paymentCost) {
        int totalCost = lottoNumbers.size() * MANUAL_LOTTO_TICKET_COST;

        validationCost(paymentCost, totalCost);

        return lottoNumbers.stream()
                .map(LottoTicket::new)
                .toList();
    }

    public List<LottoTicket> purchaseAutoLotto(int purchaseLottoCount, int paymentCost) {
        int totalCost = purchaseLottoCount * AUTO_LOTTO_TICKET_COST;

        validationCost(paymentCost, totalCost);
        return lottoNumbersGenerator.generate(purchaseLottoCount).stream()
                .map(LottoTicket::new)
                .toList();
    }

    private static void validationCost(int paymentCost, int totalCost) {
        if(totalCost > paymentCost){
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
    }
}

package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketStore {
    public static int LOTTO_TICKET_PRICE = 1_000;
    private final LottoTicketGenerator lottoTicketGenerator;

    public LottoTicketStore(LottoTicketGenerator lottoTicketGenerator){
        this.lottoTicketGenerator = lottoTicketGenerator;
    }
    public List<LottoTicket> purchaseLotto(List<List<LottoNumber>> lottoNumbers, Wallet wallet) {
        int totalPrice = lottoNumbers.size() * LOTTO_TICKET_PRICE;
        if(totalPrice > wallet.getRemainAmount()){
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }

        wallet.use(wallet.getRemainAmount() - totalPrice);

        return lottoNumbers.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> purchaseLotto(Wallet wallet) {
        int lottoCount = wallet.getRemainAmount() / LOTTO_TICKET_PRICE;
        int totalPrice = lottoCount * LOTTO_TICKET_PRICE;

        wallet.use(wallet.getRemainAmount() - totalPrice);
        return lottoTicketGenerator.generate(lottoCount);
    }
}

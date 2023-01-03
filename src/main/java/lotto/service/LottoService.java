package lotto.service;

import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.repository.LottoRepository;

import java.util.List;

public class LottoService {
    private static final Integer LOTTO_PRICE = 1000;

    private final LottoRepository lottoRepository;

    public LottoService(){
        lottoRepository = new LottoRepository();
    }

    public void purchaseLotto(Integer purchaseAmount){
        for (int count = 0; count < purchaseAmount / LOTTO_PRICE; count++) {
            lottoRepository.saveLottoTicket(new LottoTicket());
        }
    }

    public List<String> getLottoTickets() {
        LottoTickets lottoTickets = lottoRepository.getAllLottoTicket();
        return lottoTickets.toStringList();
    }


}

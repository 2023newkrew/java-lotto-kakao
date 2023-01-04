package lotto.service;

import java.util.stream.IntStream;
import lotto.model.*;
import lotto.repository.LottoRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoService {
    private static final Integer LOTTO_PRICE = 1000;

    public void purchaseLotto(PurchaseAmount purchaseAmount) {
        IntStream.range(0, purchaseAmount.getLottoTicketCount(LOTTO_PRICE))
                .forEach(index -> LottoRepository.saveLottoTicket(new LottoTicket()));
    }

    public LottoTickets getLottoTickets() {
        return LottoRepository.getAllLottoTicket();
    }

    public LottoResult getLottoResult(String inputWinningNumber, String inputBonusBall) {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(inputWinningNumber, inputBonusBall);

        return lottoTickets.getLottoResult(lottoWinningNumber);
    }

    public Double getRateOfReturn(PurchaseAmount purchaseAmount, LottoResult lottoResult) {
        return purchaseAmount.calculateRateOfReturn(lottoResult.getTotalRevenue());
    }

    public static Integer getLottoPrice() {
        return LOTTO_PRICE;
    }
}

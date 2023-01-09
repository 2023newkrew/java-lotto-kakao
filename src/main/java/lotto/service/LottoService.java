package lotto.service;

import java.util.stream.IntStream;
import lotto.model.*;
import lotto.repository.LottoRepository;

import java.util.List;

public class LottoService {
    private static final Integer LOTTO_PRICE = 1000;

    public Integer getTotalLottoCount(PurchaseAmount purchaseAmount) {
        return purchaseAmount.getLottoTicketCount(LOTTO_PRICE);
    }

    public void purchaseLotto(LottoCount lottoCount, List<String> manualLottos) {
        LottoRepository.resetLottoTickets();
        manualLottos.forEach(
                manualLotto -> LottoRepository.saveLottoTicket(new LottoTicket(manualLotto))
        );
        IntStream.range(0, lottoCount.getAutomaticLottoCount())
                .forEach(index -> LottoRepository.saveLottoTicket(new LottoTicket()));
    }

    public LottoTickets getLottoTickets() {
        return LottoRepository.getAllLottoTicket();
    }

    public LottoResult getLottoResult(LottoWinningNumber lottoWinningNumber) {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        return lottoTickets.getLottoResult(lottoWinningNumber);
    }

    public Double getRateOfReturn(PurchaseAmount purchaseAmount, LottoResult lottoResult) {
        return purchaseAmount.calculateRateOfReturn(lottoResult.getTotalRevenue());
    }

    public static Integer getLottoPrice() {
        return LOTTO_PRICE;
    }
}

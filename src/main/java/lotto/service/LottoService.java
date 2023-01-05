package lotto.service;

import java.util.stream.IntStream;
import lotto.model.*;
import lotto.repository.LottoRepository;

import java.util.List;

public class LottoService {
    private static final Integer LOTTO_PRICE = 1000;

    private PurchaseAmount purchaseAmount;
    private LottoCount lottoCount;
    private LottoWinningNumber lottoWinningNumber;

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = new PurchaseAmount(purchaseAmount);
    }

    public void setLottoCount(String manualLottoCount) {
        lottoCount = new LottoCount(purchaseAmount.getLottoTicketCount(LOTTO_PRICE), manualLottoCount);
    }

    public void setLottoWinningNumber(String inputWinningNumber, String inputBonusBall) {
        lottoWinningNumber = new LottoWinningNumber(inputWinningNumber, inputBonusBall);
    }

    public void purchaseLotto(List<String> manualLottos) {
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

    public LottoResult getLottoResult() {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        return lottoTickets.getLottoResult(lottoWinningNumber);
    }

    public Double getRateOfReturn(LottoResult lottoResult) {
        return purchaseAmount.calculateRateOfReturn(lottoResult.getTotalRevenue());
    }

    public LottoCount getLottoCount() {
        return lottoCount;
    }

    public static Integer getLottoPrice() {
        return LOTTO_PRICE;
    }
}

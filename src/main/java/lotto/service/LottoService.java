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
                .forEach(index -> {
                    LottoRepository.saveLottoTicket(new LottoTicket());
                });
    }

    public LottoTickets getLottoTickets() {
        return LottoRepository.getAllLottoTicket();
    }

    public Map<LottoRank, Integer> getLottoResult(List<Integer> inputWinningNumber, Integer inputBonusBall) {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        List<LottoNumber> lottoNumbers =
                inputWinningNumber.stream()
                        .map(number -> LottoNumber.from(number))
                        .collect(Collectors.toList());

        return lottoTickets.getLottoResult(
                new LottoWinningNumber(
                        new LottoTicket(lottoNumbers),
                        LottoNumber.from(inputBonusBall)
                )
        ).getRankCountMap();
    }

    public Double getRateOfReturn(PurchaseAmount purchaseAmount, Map<LottoRank, Integer> lottoResult) {
        int totalRevenue = 0;
        for (LottoRank lottoRank : lottoResult.keySet()) {
            totalRevenue += lottoRank.getReward() * lottoResult.get(lottoRank);
        }
        return purchaseAmount.calculateRateOfReturn(totalRevenue);
    }

    public static Integer getLottoPrice() {
        return LOTTO_PRICE;
    }
}

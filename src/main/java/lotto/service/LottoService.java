package lotto.service;

import lotto.model.*;
import lotto.repository.LottoRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoService {
    private static final Integer LOTTO_PRICE = 1000;

    public void purchaseLotto(Integer purchaseAmount){
        for (int count = 0; count < purchaseAmount / LOTTO_PRICE; count++) {
            LottoRepository.saveLottoTicket(new LottoTicket());
        }
    }

    public List<String> getLottoTickets() {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        return lottoTickets.toStringList();
    }

    public Map<LottoRank, Integer> getLottoResult(List<Integer> inputWinningNumber, Integer inputBonusBall) {
        LottoTickets lottoTickets = LottoRepository.getAllLottoTicket();
        List<LottoNumber> winningLottoNumbers =
                inputWinningNumber.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());

        return createLottoResult(
                lottoTickets,
                new LottoWinningNumber(
                        new LottoTicket(winningLottoNumbers),
                        new LottoNumber(inputBonusBall)
                )
        ).getRankCountMap();
    }

    private LottoResult createLottoResult(LottoTickets lottoTickets, LottoWinningNumber lottoWinningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            lottoResult.addLottoRankCount(
                    checkLottoRank(lottoTicket, lottoWinningNumber)
            );
        }
        return lottoResult;
    }

    private LottoRank checkLottoRank(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber){
        Integer sameCount = lottoTicket.countOverlappingNumber(lottoWinningNumber.getWinningNumber());
        boolean isBonus = lottoTicket.contains(lottoWinningNumber.getBonusBall());

        return LottoRank.fromCountAndBonus(sameCount, isBonus);
    }

    public Double getRateOfReturn(Integer inputPurchaseAmount, Map<LottoRank, Integer> lottoResult) {
        int totalRevenue = 0;
        for(LottoRank lottoRank : lottoResult.keySet()){
            totalRevenue += lottoRank.getReward() * lottoResult.get(lottoRank);
        }
        return (double) totalRevenue / inputPurchaseAmount;
    }
}

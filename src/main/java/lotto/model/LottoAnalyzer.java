package lotto.model;

import lotto.model.lotto.LottoBundle;
import lotto.model.lotto.PurchaseResult;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.Money;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoAnalyzer {

    private final WinningNumber winningNumber;

    public static LottoAnalyzer create(WinningNumber winningNumber) {
        if (Objects.isNull(winningNumber)) {
            throw new IllegalArgumentException("당첨 번호가 없습니다.");
        }

        return new LottoAnalyzer(winningNumber);
    }

    private LottoAnalyzer(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public WinningStatistics analyze(Money paidMoney, PurchaseResult purchaseResult) {
        Money totalPrice = purchaseResult.getTotalPrice();
        LottoBundle lottoBundle = purchaseResult.getLottoBundle();
        PrizeMap prizeMap = createPrizeMap(lottoBundle);

        return WinningStatistics.from(paidMoney, totalPrice, prizeMap);
    }

    private PrizeMap createPrizeMap(LottoBundle lottoBundle) {
        List<Prize> prizes = lottoBundle.stream()
                .map(winningNumber::judge)
                .collect(Collectors.toList());

        return PrizeMap.from(prizes);
    }
}

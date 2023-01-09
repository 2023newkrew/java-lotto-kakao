package domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoShop {

    public static final int LOTTO_PRICE = 1000;

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoShop(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public LottoTickets buyLottoTickets(Cost cost) {
        return buyLottoTickets(Collections.emptyList(), cost);
    }

    public LottoTickets buyLottoTickets(List<LottoNumbers> manualLottoNumbers, Cost cost) {
        validate(manualLottoNumbers, cost);
        List<LottoNumbers> autoLottoNumbers =
                IntStream.range(0, countAvailableAutoLottoPurchase(manualLottoNumbers, cost))
                .mapToObj(idx -> lottoNumbersGenerator.generate())
                .collect(Collectors.toList());
        return new LottoTickets(autoLottoNumbers, manualLottoNumbers);
    }

    private int countAvailableAutoLottoPurchase(List<LottoNumbers> manualLottoNumbers, Cost cost) {
        return cost.countAvailablePurchases(LOTTO_PRICE) - manualLottoNumbers.size();
    }

    private void validate(List<LottoNumbers> manualLottoNumbers, Cost cost) {
        int availablePurchaseCount = cost.countAvailablePurchases(LOTTO_PRICE);
        if (availablePurchaseCount <= 0 || availablePurchaseCount < manualLottoNumbers.size()) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
    }
}

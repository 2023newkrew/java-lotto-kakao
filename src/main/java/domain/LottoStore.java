package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore implements LottoPurchasePlace {
    private static final Integer LOTTO_COST = 1000;
    private static final String ERROR_MONEY_LOWER_THEN_COST = "로또를 구매하기 위한 금액이 부족합니다.";
    private static final String ERROR_PURCHASE_NOTHING = "로또를 ";

    @Override
    public List<Lotto> purchase(Integer money, List<Lotto> manualLottos) {
        Integer remainMoney = money - manualLottos.size() * LOTTO_COST;
        if (remainMoney < 0) {
            throw new IllegalArgumentException(ERROR_MONEY_LOWER_THEN_COST);
        }

        List<Lotto> result = new ArrayList<>(manualLottos);
        if (remainMoney >= LOTTO_COST) {
            result.addAll(purchaseAuto(remainMoney));
        }

        if (result.size()==0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_NOTHING);
        }
        return result;
    }

    private List<Lotto> purchaseAuto(Integer money) {
        return IntStream
                .range(0, getAmount(money))
                .mapToObj((number) -> Lotto.ofAuto())
                .collect(Collectors.toList());
    }
    private Integer getAmount(Integer money) {
        return money / LOTTO_COST;
    }
}

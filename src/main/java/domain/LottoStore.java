package domain;

import dto.PurchasedLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore implements LottoPurchasePlace {
    private static final Integer LOTTO_COST = 1000;
    private static final String ERROR_MONEY_LOWER_THAN_COST = "로또를 구매하기 위한 금액이 부족합니다.";
    private static final String ERROR_PURCHASE_NOTHING = "구매 금액이 로또 가격보다 작아 구매할 수 없습니다.";

    @Override
    public PurchasedLotto purchase(Integer money, List<Lotto> manualLottos) {
        validateMoney(money, manualLottos.size());

        List<Lotto> autoLottos = new ArrayList<>();
        Integer remainMoney = money - manualLottos.size() * LOTTO_COST;
        if (remainMoney >= LOTTO_COST) {
            autoLottos = purchaseAuto(remainMoney);
        }
        return new PurchasedLotto(manualLottos, autoLottos);
    }

    private List<Lotto> purchaseAuto(Integer money) {
        return IntStream
                .range(0, getAmount(money))
                .mapToObj((number) -> Lotto.ofAuto())
                .collect(Collectors.toList());
    }

    private void validateMoney(Integer money, Integer amount) {
        if (money < LOTTO_COST) {
            throw new IllegalArgumentException(ERROR_PURCHASE_NOTHING);
        }
        if ((money - (amount * LOTTO_COST)) < 0) {
            throw new IllegalArgumentException(ERROR_MONEY_LOWER_THAN_COST);
        }
    }

    private Integer getAmount(Integer money) {
        return money / LOTTO_COST;
    }
}

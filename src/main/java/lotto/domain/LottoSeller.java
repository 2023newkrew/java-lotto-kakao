package lotto.domain;

import lotto.utils.LottoGenerator;

import java.util.List;

public class LottoSeller {
    public final static int LOTTO_PRICE = 1000;

    public static int getLottoAmount(Money money) {
        if (money.money() < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 한 장의 가격보다 커야 합니다.");
        }
        return money.money() / LOTTO_PRICE;
    }

    public static List<UserLotto> generateRandomLottos(int randomAmount) {
        return LottoGenerator.generateRandomLottos(randomAmount);
    }

    public static List<UserLotto> generateManualLottos(List<List<Integer>> manualNumbersGroup) {
        return LottoGenerator.generateManualLottos(manualNumbersGroup);
    }

}

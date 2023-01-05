package domain;

import dto.PurchasedLotto;

import java.util.List;

public interface LottoPurchasePlace {
    PurchasedLotto purchase(Integer money, List<Lotto> manualLottos);
}

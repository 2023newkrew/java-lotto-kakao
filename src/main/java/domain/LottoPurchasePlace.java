package domain;

import java.util.List;

public interface LottoPurchasePlace {
    List<Lotto> purchase(Integer money, List<Lotto> manualLottos);
}

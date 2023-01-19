package domain;

import java.util.List;

public interface LottoStore {
    final Integer COST = 1000;

    List<Lotto> buy(Integer amount);
}

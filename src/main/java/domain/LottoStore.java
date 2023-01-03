package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore implements LottoObtainPlace {
    private static final Integer LOTTO_COST = 1000;
    private static final String ERROR_MONEY_LOWER_THEN_COST = "로또를 구매하기 위한 최소 금액이 부족합니다.";

    @Override
    public List<Lotto> obtain(Integer money) {
        Integer amount = getAmount(money);

        return IntStream
                .range(0, amount)
                .mapToObj((number) -> new Lotto())
                .collect(Collectors.toList());
    }

    private Integer getAmount(Integer money) {
        if (money < LOTTO_COST) {
            throw new IllegalArgumentException(ERROR_MONEY_LOWER_THEN_COST);
        }
        return money / LOTTO_COST;
    }
}

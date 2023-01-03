package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore implements LottoObtainPlace {
    private static final Integer COST = 1000;

    @Override
    public List<Lotto> buy(Integer money) {
        Integer amount = getLottoAmount(money);

        return IntStream
                .range(0, amount)
                .mapToObj((number) -> new Lotto())
                .collect(Collectors.toList());
    }

    private Integer getLottoAmount(Integer money) {
        if (money < COST) {
            throw new IllegalArgumentException("로또를 구매하기 위한 최소 금액이 부족합니다.");
        }
        return money / COST;
    }
}

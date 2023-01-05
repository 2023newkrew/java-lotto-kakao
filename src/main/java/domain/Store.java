package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.ErrorMessage.NOT_ENOUGH_MONEY;

public class Store implements LottoObtainPlace {
    private static final Integer COST = 1000;

    @Override
    public List<Lotto> buy(Integer money) {
        Integer amount = getLottoAmount(money);

        return IntStream.range(0, amount)
                .mapToObj((number) -> new Lotto())
                .collect(Collectors.toList());
    }

    private Integer getLottoAmount(Integer money) {
        if (money < COST) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY.getMessage());
        }
        return money / COST;
    }
}

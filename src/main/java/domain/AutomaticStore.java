package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutomaticStore implements LottoStore {

    @Override
    public List<Lotto> buy(Integer amount) {
        return IntStream.range(0, amount)
                .mapToObj((number) -> new Lotto())
                .collect(Collectors.toList());
    }

}

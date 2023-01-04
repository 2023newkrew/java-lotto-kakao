package lotto.models;

import static lotto.common.LottoConfiguration.MAX_VALUE;
import static lotto.common.LottoConfiguration.MIN_VALUE;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LotteryNumber {

    public static final Map<Integer, LotteryNumber> numbers = new HashMap<>() {{
        IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .forEach(number -> numbers.put(number, new LotteryNumber(number)));
    }};

    private final Integer number;

    private LotteryNumber(Integer number) {
        this.number = number;
    }

    public static LotteryNumber from(Integer number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new RuntimeException("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
        return numbers.get(number);
    }

    public boolean compare(Integer number) {
        return Objects.equals(this.number, number);
    }
}

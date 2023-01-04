package lotto.models;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LotteryNumber {

    static public final Integer MIN_VALUE = 1;

    static public final Integer MAX_VALUE = 45;

    private static final Map<Integer, LotteryNumber> numbers = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .forEach(number -> numbers.put(number, new LotteryNumber(number)));
    }

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

    public Integer getNumber() {
        return number;
    }
}

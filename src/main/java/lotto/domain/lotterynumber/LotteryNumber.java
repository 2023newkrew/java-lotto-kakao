package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBER_OUT_OF_RANGE;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MAXIMIM;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MINIMUM;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LotteryNumber {
    private static final Map<Integer, LotteryNumber> lottoNumberMap = new HashMap<>(){{
        IntStream.range(LOTTERY_NUMBER_MINIMUM, LOTTERY_NUMBER_MAXIMIM)
                .forEach(number -> put(number, new LotteryNumber(number)));
    }};
    private final int value;

    private LotteryNumber(int value) {
        this.value = value;
    }

    public static LotteryNumber of(int value) {
        validate(value);
        return lottoNumberMap.get(value);
    }

    private static void validate(int value) {
        if (value < LOTTERY_NUMBER_MINIMUM || value > LOTTERY_NUMBER_MAXIMIM) {
            throw new IllegalArgumentException(LOTTERY_NUMBER_OUT_OF_RANGE);
        }
    }

    public int compareTo(LotteryNumber otherNumber) {
        return Integer.compare(this.value, otherNumber.value);
    }

    public int getValue() {
        return value;
    }
}

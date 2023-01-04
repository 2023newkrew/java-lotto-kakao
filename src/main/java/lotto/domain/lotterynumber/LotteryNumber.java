package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBER_OUT_OF_RANGE;
import static lotto.constant.LotteryConstant.LOTTO_NUMBER_MAXIMIM;
import static lotto.constant.LotteryConstant.LOTTO_NUMBER_MINIMUM;

import java.util.HashMap;
import java.util.Map;

public class LotteryNumber {
    private static final Map<Integer, LotteryNumber> lottoNumberMap = new HashMap<>();
    private final int value;

    private LotteryNumber(int value) {
        this.value = value;
    }

    public static LotteryNumber of(int value) {
        validate(value);
        return getLotteryNumber(value);
    }

    private static LotteryNumber getLotteryNumber(int value) {
        if (!lottoNumberMap.containsKey(value)) {
            lottoNumberMap.put(value, new LotteryNumber(value));
        }
        return lottoNumberMap.get(value);
    }

    private static void validate(int value) {
        if (value < LOTTO_NUMBER_MINIMUM || value > LOTTO_NUMBER_MAXIMIM) {
            throw new IllegalArgumentException(LOTTERY_NUMBER_OUT_OF_RANGE);
        }
    }

    public int compareTo(LotteryNumber otherNumber) {
        return Integer.compare(this.value, otherNumber.value);
    }
}

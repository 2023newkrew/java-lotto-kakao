package lotto.domain.lotterynumber;

import static lotto.constant.LotteryConstant.LOTTERY_NUMBERS_LENGTH;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MAXIMUM;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBER_MINIMUM;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLotteryNumberCombinationFactory {
    private static final List<LotteryNumber> lottoNumbersPool =
            IntStream.range(LOTTERY_NUMBER_MINIMUM, LOTTERY_NUMBER_MAXIMUM)
                    .mapToObj(LotteryNumber::of)
                    .collect(Collectors.toList());

    public static LotteryNumberCombination create() {
        Collections.shuffle(lottoNumbersPool);
        Set<LotteryNumber> subset = new HashSet<>(lottoNumbersPool.subList(0, LOTTERY_NUMBERS_LENGTH));
        return new LotteryNumberCombination(subset);
    }

    public static List<LotteryNumberCombination> createMany(int count) {
        validateCount(count);
        return IntStream.range(0, count).mapToObj(i -> create()).collect(Collectors.toList());
    }

    private static void validateCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 0개 이상만 생성할 수 있습니다.");
        }
    }
}

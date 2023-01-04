package lotto.common;

import static lotto.common.LotteryConfiguration.LOTTERY_COUNT;
import static lotto.common.LotteryConfiguration.MAX_VALUE;
import static lotto.common.LotteryConfiguration.MIN_VALUE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.models.Lottery;

public class LotteryGenerator {
    static private final List<Integer> lotteryPreset = IntStream
            .rangeClosed(MIN_VALUE, MAX_VALUE)
            .boxed()
            .collect(Collectors.toList());

    public static Lottery createLottery() {
        Collections.shuffle(lotteryPreset);
        List<Integer> numbers = lotteryPreset.subList(0, LOTTERY_COUNT).stream()
                .sorted()
                .collect(Collectors.toList());
        return new Lottery(numbers);
    }

    public static List<Lottery> createLotteries(int numberOfLottery) {
        List<Lottery> list = new ArrayList<>();
        for (int i = 0; i < numberOfLottery; i++) {
            list.add(createLottery());
        }
        return list;
    }
}
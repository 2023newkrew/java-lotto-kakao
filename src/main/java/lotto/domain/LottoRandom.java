package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoRandom {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_MAX_NUMBER = 45;

    public ArrayList<Integer> createRandomNumbers(){
        List<Integer> numList = IntStream.range(1, LOTTO_MAX_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numList);
        numList = numList.subList(0, LOTTO_NUMBER_COUNT);
        Collections.sort(numList);
        return new ArrayList<>(numList);
    }
}
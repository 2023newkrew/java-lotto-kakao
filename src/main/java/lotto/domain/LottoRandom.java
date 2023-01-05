package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.utils.Constants.LOTTO_TICKET_SIZE;
import static lotto.utils.Constants.LOTTO_UPPER_BOUND;

public class LottoRandom {

    public ArrayList<Integer> createRandomNumbers(){
        List<Integer> numList = IntStream.range(1, LOTTO_UPPER_BOUND + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numList);
        numList = numList.subList(0, LOTTO_TICKET_SIZE);
        Collections.sort(numList);
        return new ArrayList<>(numList);
    }
}
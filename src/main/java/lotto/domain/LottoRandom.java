package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoRandom {
    private final Random random;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public LottoRandom(){
        this.random = new Random();
    }

    public ArrayList<Integer> createRandomNumbers(){
        Set<Integer> randomNumbersSet = new HashSet<>();
        while (randomNumbersSet.size() != LOTTO_NUMBER_COUNT) {
            randomNumbersSet.add(random.nextInt(45)+1);
        }
        return new ArrayList<>(randomNumbersSet);
    }
}

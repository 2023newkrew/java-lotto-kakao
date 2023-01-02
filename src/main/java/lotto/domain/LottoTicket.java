package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoTicket {

    private final ArrayList<Integer> lottoNumbers;
    private static int LOTTO_NUMBER_COUNT = 6;

    public LottoTicket() {
        this.lottoNumbers = createRandomNumbers();
    }

    public ArrayList<Integer> createRandomNumbers(){
        Random random = new Random();
        Set<Integer> randomNumbersSet = new HashSet<>();
        while (randomNumbersSet.size() != LOTTO_NUMBER_COUNT) {
            randomNumbersSet.add(random.nextInt(45)+1);
        }
        return new ArrayList<>(randomNumbersSet);
    }


    public ArrayList<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }
}

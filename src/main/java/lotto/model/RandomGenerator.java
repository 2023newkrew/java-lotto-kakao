package lotto.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    private final LottoNumbers lottoNumbers;
    private final List<Integer> candidate;

    public RandomGenerator(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        this.candidate = new ArrayList<>();
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            this.candidate.add(i);
        }
    }

    public List<LottoNumber> getOrderedNumbers(int quantity) {
        Collections.shuffle(this.candidate);
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            numbers.add(lottoNumbers.get(this.candidate.get(i)));
        }
        return numbers;
    }
}

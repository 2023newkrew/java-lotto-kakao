package lotto.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumbersGenerator {
    private final LottoNumbers lottoNumbers;
    private final List<Integer> candidate;

    public RandomNumbersGenerator(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        this.candidate = new ArrayList<>();
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            this.candidate.add(i);
        }
    }

    public List<LottoNumber> getOrderedNumbers() {
        Collections.shuffle(this.candidate);
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 0; i < LottoTicket.NUMBERS_LENGTH; i++) {
            numbers.add(this.lottoNumbers.get(this.candidate.get(i)));
        }
        return numbers;
    }
}

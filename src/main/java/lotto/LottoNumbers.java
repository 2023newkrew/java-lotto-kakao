package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    private static final int LOTTERY_NUMBER_SIZE = 6;
    private final List<LottoNumber> numbers = new ArrayList<>();

    public LottoNumbers(List<Integer> numbers) {
        validateNumbers(numbers);

        for (Integer number : numbers) {
            this.numbers.add(new LottoNumber(number));
        }
        
        Collections.sort(this.numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (!isValidSize(numbers) || isDuplicateNumber(numbers))
            throw new IllegalArgumentException("잘못된 로또 번호 입력입니다");
    }

    private boolean isValidSize(List<Integer> numbers) {
        return numbers.size() == LOTTERY_NUMBER_SIZE;
    }

    private boolean isDuplicateNumber(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LottoNumbers)) return false;

        LottoNumbers cp = (LottoNumbers) obj;

        return this.numbers.equals(cp.numbers);
    }
}

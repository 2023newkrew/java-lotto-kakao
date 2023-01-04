package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTERY_NUMBER_SIZE = 6;

    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(String numbersAsString) {
        this(parseStringToIntList(numbersAsString));
    }

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        for (Integer number : numbers) {
            this.numbers.add(new LottoNumber(number));
        }
        Collections.sort(this.numbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int compareWith(Lotto lotto) {
        return (int) this.numbers.stream()
                .filter(lotto.numbers::contains)
                .count();
    }

    public boolean containsLottoNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    private static List<Integer> parseStringToIntList(String text){
        String[] texts = text.split(",");
        return Arrays.stream(texts).map(t -> Integer.parseInt(t.strip())).collect(Collectors.toList());
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lotto)) return false;

        Lotto cp = (Lotto) obj;

        return this.numbers.equals(cp.numbers);
    }
}

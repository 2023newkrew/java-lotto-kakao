package lotto.model.ticket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {
    public static final int NUMBERS_LENGTH = 6;
    public static final long PRICE = 1000L;

    private final HashSet<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        this.numbers = this.getValidNumbers(numbers);
    }

    private HashSet<LottoNumber> getValidNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != LottoTicket.NUMBERS_LENGTH) {
            throw new IllegalArgumentException("로또는 6개의 번호를 가져야 합니다.");
        }
        boolean isValid = true;
        HashSet<LottoNumber> validNumbers = new HashSet<>();
        for (LottoNumber number : numbers) {
            isValid = isValid && validNumbers.add(number);
        }
        if (!isValid) {
            throw new IllegalArgumentException("로또는 중복된 번호를 가질 수 없습니다.");
        }
        return validNumbers;
    }

    public HashSet<LottoNumber> getNumbers() {
        return this.numbers;
    }

    public boolean contains(LottoNumber number) {
        return this.numbers.contains(number);
    }

    public String toString() {
        List<String> numbers = new ArrayList<>();
        for (LottoNumber number : this.numbers) {
            numbers.add(String.valueOf(number.getNumber()));
        }
        return "[" + String.join(", ", numbers) + "]";
    }
}
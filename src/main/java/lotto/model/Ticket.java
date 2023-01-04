package lotto.model;

import java.util.HashSet;
import java.util.List;

public class Ticket {
    public static final int NUMBERS_LENGTH = 6;

    private final HashSet<LottoNumber> numbers;

    public Ticket(List<LottoNumber> numbers) {
        this.numbers = this.getValidNumbers(numbers);
    }

    private HashSet<LottoNumber> getValidNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != Ticket.NUMBERS_LENGTH) {
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

    public boolean contains(LottoNumber number) {
        return this.numbers.contains(number);
    }
}
package lotto.model.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int NUMBERS_LENGTH = 6;
    public static final long PRICE = 1000L;

    private final HashSet<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        this.numbers = this.getValidNumbers(numbers);
    }

    private HashSet<LottoNumber> getValidNumbers(List<LottoNumber> numbers) {
        HashSet<LottoNumber> validNumbers = new HashSet<>(numbers);
        if (validNumbers.size() != LottoTicket.NUMBERS_LENGTH) {
            throw new IllegalArgumentException("로또는 중복되지 않는 6개의 번호를 가져야 합니다.");
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
        List<Integer> numbers = new ArrayList<>();
        for (LottoNumber number : this.numbers) {
            numbers.add(number.getNumber());
        }
        Collections.sort(numbers);
        return "[" + String.join(", ", numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList())) + "]";
    }
}
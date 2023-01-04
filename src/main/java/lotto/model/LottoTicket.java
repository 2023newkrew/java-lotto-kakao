package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    public static final int NUMBERS_SIZE = 6;
    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateValuesCount(numbers);
        validateDistinction(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public LottoTicket(LottoTicketDto ticketDto) {
        List<LottoNumber> numbers = ticketDto.getTicket()
                .stream().map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        validateValuesCount(numbers);
        validateDistinction(numbers);
        this.numbers = numbers;
    }

    private void validateValuesCount(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다");
        }
    }

    private void validateDistinction(List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다");
        }
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public Stream<LottoNumber> stream() {
        return numbers.stream();
    }

    @Override
    public String toString() {
        return "LottoTicket{" +
                "numbers=" + numbers +
                '}';
    }
}

package lottov2.model.ticket;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class LottoNumber {

    private static final int SIZE = 6;

    private final Set<SingleLottoNumber> numbers;

    public static LottoNumber of(Set<SingleLottoNumber> numbers) {
        if (isInvalid(numbers)) {
            throw new IllegalArgumentException("로또 번호는 " + SIZE + "자리 숫자입니다.");
        }

        return new LottoNumber(numbers);
    }

    private static boolean isInvalid(Set<SingleLottoNumber> numbers) {
        return Objects.isNull(numbers) || numbers.size() != SIZE;
    }

    private LottoNumber(Set<SingleLottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static LottoNumber createByRandom(){
        List<SingleLottoNumber> numberCandidate = SingleLottoNumber.getAllNumbersInRange();
        Collections.shuffle(numberCandidate);

        return LottoNumber.of(Set.copyOf(numberCandidate.subList(0, SIZE)));
    }

    public boolean hasNumber(SingleLottoNumber singleNumber){
        return numbers.contains(singleNumber);
    }

    public int countCommonNumber(LottoNumber other) {
        return (int) numbers.stream()
                .filter(other::hasNumber)
                .count();
    }

    public Stream<SingleLottoNumber> stream() {
        return numbers.stream();
    }
}

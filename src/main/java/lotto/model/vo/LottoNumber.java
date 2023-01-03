package lotto.model.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 45;

    private final int value;

    public static List<LottoNumber> createNumberPool() {
        return IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoNumber valueOf(int number) {
        if (isOutOfRange(number)) {
            String errorMessage = String.format("로또 번호는 %d ~ %d 사이의 숫자입니다.", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }

        return new LottoNumber(number);
    }

    private static boolean isOutOfRange(int number) {
        return number < MIN_NUMBER || number > MAX_NUMBER;
    }


    private LottoNumber(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static List<LottoNumber> generateRandomNumbers(int size) {
        List<LottoNumber> numberPool = createNumberPool();
        Collections.shuffle(numberPool);

        return numberPool.subList(0, size);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

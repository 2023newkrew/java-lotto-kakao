package lotto.model.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleLottoNumber implements Comparable<SingleLottoNumber> {

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 45;

    private static final List<SingleLottoNumber> ALL_NUMBERS_IN_RANGE = createAllNumbersInRange();

    private final int value;

    public static SingleLottoNumber valueOf(int value) {
        if (isOutOfRange(value)) {
            String errorMessage = String.format("로또 번호는 %d ~ %d 사이의 숫자입니다.", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(errorMessage);
        }

        return new SingleLottoNumber(value);
    }

    private SingleLottoNumber(int value) {
        this.value = value;
    }

    private static boolean isOutOfRange(int value) {
        return value < MIN_NUMBER || value > MAX_NUMBER;
    }


    private static List<SingleLottoNumber> createAllNumbersInRange() {
        return IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .mapToObj(SingleLottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    public static List<SingleLottoNumber> getAllNumbersInRange() {
        return new ArrayList<>(ALL_NUMBERS_IN_RANGE);
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SingleLottoNumber)) {
            return false;
        }
        SingleLottoNumber that = (SingleLottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(SingleLottoNumber o) {
        return Integer.compare(value, o.value);
    }
}


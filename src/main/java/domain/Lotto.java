package domain;

import common.state.Result;
import util.validator.LottoValidator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    private static final int START_INDEX = 0;
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    public static final int LENGTH = 6;
    public static final String DELIMITER = ", ";
    private static final List<LottoNumber> WHOLE_NUMBERS = IntStream.rangeClosed(MINIMUM, MAXIMUM)
            .boxed().map(number -> new LottoNumber(number))
            .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto getAutoLotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<LottoNumber> numbers = new ArrayList<>(WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + LENGTH));
        numbers.stream().sorted();
        return new Lotto(numbers);
    }

    public static Lotto getManualLotto(String input) {
        LottoValidator.validate(input);
        List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                .map(inputString -> new LottoNumber(inputString))
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public int getMatchCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::containsNumber)
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public String lottoToString() {
        return numbers.stream()
                .map(number -> number.number)
                .collect(Collectors.toList())
                .toString();
    }
}

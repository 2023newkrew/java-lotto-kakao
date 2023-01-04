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
        numbers.sort(new Comparator<LottoNumber>() {
            @Override
            public int compare(LottoNumber o1, LottoNumber o2) {
                return o1.number - o2.number;
            }
        });
        return new Lotto(numbers);
    }

    public static Lotto getManualLotto(String input) {
        LottoValidator.validate(input);
        List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                .map(inputString -> new LottoNumber(inputString))
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public Result getResult(WinningLottoWithBonus winningLottoWithBonus) {
        Lotto winningLottoNumbers = winningLottoWithBonus.getWinningLotto();
        int count = (int) numbers.stream()
                .filter(winningLottoNumbers::containsNumber)
                .count();
        return Result.of(count, winningLottoWithBonus.isBonus(this));
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

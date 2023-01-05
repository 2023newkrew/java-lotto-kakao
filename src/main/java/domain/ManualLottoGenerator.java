package domain;

import util.validator.LottoValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualLottoGenerator implements LottoGenerator {

    public static final String DELIMITER = ", ";
    private final String input;

    public ManualLottoGenerator(String input) {
        this.input = input;
    }

    @Override
    public Lotto generateLotto() {
        LottoValidator.validate(input);
        List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                .map(inputString -> new LottoNumber(inputString))
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }
}

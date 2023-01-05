package domain;

import exception.DuplicateNumberException;
import exception.IllegalLengthException;

import java.util.*;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    public static final String DELIMITER = ", ";
    private final String input;

    public ManualLottoGenerator(String input) {
        this.input = input;
    }

    @Override
    public Lotto generateLotto() {
        validate(input);
        List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                .map(inputString -> new LottoNumber(inputString))
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public static void validate(String input) {
        String[] splitInput = input.split(ManualLottoGenerator.DELIMITER);
        validateLength(splitInput);
        Arrays.stream(splitInput).forEach(LottoNumber::validate);
        List<Integer> numbers = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        validDuplicate(numbers);
    }

    private static void validateLength(String[] inputs) {
        if (inputs.length == 1) {
            throw new IllegalArgumentException("구분자는 `, ` 입니다.");
        }
        if (inputs.length != AutoLottoGenerator.LENGTH) {
            throw new IllegalLengthException("총 여섯 개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    private static void validDuplicate(List<Integer> inputs) {
        Set<Integer> distinctNumbers = new HashSet<>();
        inputs.stream().forEach(input -> distinctNumbers.add(input));
        if (distinctNumbers.size() != AutoLottoGenerator.LENGTH) {
            throw new DuplicateNumberException("중복된 숫자가 있습니다.");
        }
    }

}

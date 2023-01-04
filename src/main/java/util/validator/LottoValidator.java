package util.validator;

import domain.Lotto;
import exception.DuplicateNumberException;
import exception.IllegalLengthException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoValidator {


    public static void validate(String input) {
        String[] splitInput = input.split(Lotto.DELIMITER);
        validateLength(splitInput);
        Arrays.stream(splitInput).forEach(LottoNumberValidator::validate);
        List<Integer> numbers = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        validDuplicate(numbers);
    }

    private static void validateLength(String[] inputs) {
        if (inputs.length == 1) {
            throw new IllegalArgumentException("구분자는 `, ` 입니다.");
        }
        if (inputs.length != Lotto.LENGTH) {
            throw new IllegalLengthException("총 여섯 개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    private static void validDuplicate(List<Integer> inputs) {
        Set<Integer> distinctNumbers = new HashSet<>();
        inputs.stream().forEach(input -> distinctNumbers.add(input));
        if (distinctNumbers.size() != Lotto.LENGTH) {
            throw new DuplicateNumberException("중복된 숫자가 있습니다.");
        }
    }


}

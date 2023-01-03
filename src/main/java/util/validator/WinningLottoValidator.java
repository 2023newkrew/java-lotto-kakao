package util.validator;

import exception.DuplicateNumberException;
import exception.IllegalLengthException;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLottoValidator {

    public static final String DELIMITER = ", ";
    public static final int VALID_LENGTH = 6;

    public static void validate(String input) {
        String[] splitInput = input.split(DELIMITER);
        validateLength(splitInput);
        Arrays.stream(splitInput).forEach(SingleNumberValidator::validate);
        List<Integer> numbers = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        validDuplicate(numbers);
    }

    private static void validateLength(String[] inputs) {
        if (inputs.length != VALID_LENGTH) {
            throw new IllegalLengthException("길이");
        }
    }

    private static void validDuplicate(List<Integer> inputs) {
        Set<Integer> distinctNumbers = new HashSet<>();
        inputs.stream().forEach(input -> distinctNumbers.add(input));
        if (distinctNumbers.size() != VALID_LENGTH) {
            throw new DuplicateNumberException("");
        }
    }


}

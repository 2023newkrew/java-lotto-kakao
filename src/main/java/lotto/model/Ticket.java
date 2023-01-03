package lotto.model;

import java.util.List;

public class Ticket {
    public static final int NUMBERS_LENGTH = 6;
    public static final int NUMBERS_RANGE = 45;

    private final List<Integer> numbers;

    public Ticket(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static boolean isValidNumber(int number) {
        return number > 0 && number <= Ticket.NUMBERS_RANGE;
    }

    public static boolean isValidNumbers(List<Integer> numbers) {
        if (numbers.size() != Ticket.NUMBERS_LENGTH) {
            return false;
        }
        boolean isValid = true;
        for (int number : numbers) {
            isValid = isValid && Ticket.isValidNumber(number);
        }
        return isValid;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
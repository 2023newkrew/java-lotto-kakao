package domain;

import exception.NegativeArgumentException;

public class Count {

    private final int count;
    private final int totalCount;

    public Count(String count, int totalCount) {
        validate(count, totalCount);
        this.count = Integer.parseInt(count);
        this.totalCount = totalCount;
    }

    private static void validate(String input, int totalCount) {
        validateNumberFormat(input);
        int count = Integer.parseInt(input);
        validatePositive(count);
        validateBudget(count, totalCount);
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 문자열이 입력되었습니다.");
        }
    }

    private static void validatePositive(int count) {
        if (count < 0) {
            throw new NegativeArgumentException("개수는 항상 양수입니다.");
        }
    }

    private static void validateBudget(int count, int totalCount) {
        if (count > totalCount) {
            throw new IllegalArgumentException("금액이 모자랍니다.");
        }
    }

    public int getCount() {
        return count;
    }

    public boolean zero() {
        return this.count == 0;
    }

    public int getRemains() {
        return totalCount - this.count;
    }

}

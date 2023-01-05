package domain;

import util.validator.CountValidator;

public class Count {

    private final int count;
    private final int totalCount;

    public Count(String count, int totalCount) {
        CountValidator.validate(count, totalCount);
        this.count = Integer.parseInt(count);
        this.totalCount = totalCount;
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

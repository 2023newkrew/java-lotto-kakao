package domain;

import util.validator.CountValidator;

public class Count {

    private final int count;
    public final boolean zero;
    public final int remains;

    public Count(String count, int totalCount) {
        CountValidator.validate(count, totalCount);
        this.count = Integer.parseInt(count);
        zero = this.count == 0;
        remains = totalCount - this.count;
    }

    public int getCount() {
        return count;
    }

}

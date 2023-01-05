package lotto.model;

public class Prize extends Cash {
    public Prize(Cash cash) {
        this(cash.getCash());
    }

    public Prize(long cash) {
        super(cash);
    }
}

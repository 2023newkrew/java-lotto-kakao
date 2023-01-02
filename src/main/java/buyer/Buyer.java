package buyer;

import lotto.Lottery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Buyer {
    private final List<Lottery> lotteries = new ArrayList<>();
    private int budget;

    public Buyer(int budget) {
        this.budget = budget;
        for (int i = 0; i < budget; i += 1000) {
            lotteries.add(new Lottery());
        }
    }

    public Collection<Lottery> getLotteries() {
        return lotteries;
    }
}

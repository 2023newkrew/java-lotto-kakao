package buyer;

import lotto.Lotto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Buyer {
    private final List<Lotto> lotteries = new ArrayList<>();
    private int budget;

    public Buyer(int budget) {
        this.budget = budget;
        for (int i = 0; i < budget; i += 1000) {
            lotteries.add(new Lotto());
        }
    }

    public Collection<Lotto> getLotteries() {
        return lotteries;
    }
}

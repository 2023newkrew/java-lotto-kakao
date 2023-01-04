package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotteries {
    private final List<Lotto> lotteries = new ArrayList<>();

    public void addLottery(Lotto lotto) {
        lotteries.add(lotto);
    }

    public List<Lotto> getLotteries() { return Collections.unmodifiableList(lotteries); }
}

package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotteries {
    private final List<Lottery> lotteries = new ArrayList<>();

    public void addLottery(Lottery lottery) {
        lotteries.add(lottery);
    }

    public List<Lottery> getLotteries() { return Collections.unmodifiableList(lotteries); }
}

package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotteries {
    private final List<Lottery> lotteries = new ArrayList<>();

    public void addLottery(Lottery lottery) {
        lotteries.add(lottery);
    }

    public List<Lottery> getLotteries() {
        return Collections.unmodifiableList(lotteries);
    }

    public List<LotteryDTO> getLotteryDTOs() {
        return lotteries.stream().map(Lottery::toDTO).collect(Collectors.toList());
    }
}

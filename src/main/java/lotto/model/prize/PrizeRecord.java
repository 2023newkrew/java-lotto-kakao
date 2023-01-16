package lotto.model.prize;

import java.util.Arrays;
import java.util.HashMap;

public class PrizeRecord {
    private final HashMap<Prize, Integer> record;

    public PrizeRecord() {
        record = new HashMap<>();
        for (Prize prize : Prize.values()) {
            this.record.put(prize, 0);
        }
    }

    public int getCountOf(Prize prize) {
        return this.record.get(prize);
    }

    public void addCountOf(Prize prize) {
        this.record.put(prize, this.record.get(prize) + 1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Prize prize : Arrays.copyOfRange(Prize.values(), 1, Prize.values().length)) {
            sb.append(String.format("%s (%d원) - %d개\n",
                    prize.matchDescription(),
                    prize.prize(),
                    this.getCountOf(prize)
            ));
        }
        return sb.toString();
    }
}

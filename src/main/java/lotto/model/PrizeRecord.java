package lotto.model;

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
}

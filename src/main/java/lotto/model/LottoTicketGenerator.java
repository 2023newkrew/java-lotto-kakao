package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketGenerator {
    public LottoTicket generate() {
        List<LottoValue> nums = new ArrayList<>();
        for (int i = 1 ; i <= 45 ; i++){
            nums.add(new LottoValue(i));
        }
        Collections.shuffle(nums);
        return new LottoTicket(nums.subList(0, 6));
    }
}

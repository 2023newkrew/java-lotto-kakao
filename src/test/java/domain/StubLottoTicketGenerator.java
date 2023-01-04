package domain;

import java.util.ArrayList;
import java.util.List;

public class StubLottoTicketGenerator{

    private final int[] values;
    public StubLottoTicketGenerator(int ... values){
        this.values = values;

    }

    public LottoTicket generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for(int value : values){
            lottoNumbers.add(new LottoNumber(value));
        }
        return new LottoTicket(lottoNumbers);
    }
}

package domain.lotto.ticket;

import domain.lotto.generator.LottoNumberGenerator;
import domain.lotto.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketList {
    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public LottoTicketList() {
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public void addManualLottoTickets(List<LottoTicket> lottoTicketList){
        lottoTickets.addAll(lottoTicketList);
    }

    public void addAutoLottoTickets(int autoCount) {
        LottoNumberGenerator lottoNumber = new LottoNumberGenerator();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        for (int i = 0; i < autoCount; i++) {
            this.lottoTickets.add(new LottoTicket(lottoNumber.makeNumbers(randomNumberGenerator)));
        }
    }
}

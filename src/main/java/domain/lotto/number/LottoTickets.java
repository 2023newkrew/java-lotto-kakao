package domain.lotto.number;

import domain.lotto.number.LottoNumbers;

import java.util.List;

public class LottoTickets {
    private final List<LottoNumbers> lottoTickets;

    public LottoTickets(final List<LottoNumbers> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<LottoNumbers> getLottoTickets() {
        return lottoTickets;
    }

    public void addTickets(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.getLottoTickets());
    }

}

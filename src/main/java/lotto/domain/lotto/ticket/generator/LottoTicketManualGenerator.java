package lotto.domain.lotto.ticket.generator;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.ticket.LottoTicket;
import lotto.view.LottoInputView;

import java.util.List;

public class LottoTicketManualGenerator implements LottoTicketGenerator {

    private final LottoInputView lottoInputView = new LottoInputView();

    @Override
    public LottoTicket generate() {
        List<LottoNumber> lottoNumbers = lottoInputView.readLottoNumbers();
        return new LottoTicket(lottoNumbers);
    }
}

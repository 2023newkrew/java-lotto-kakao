/**
 *  model에서 생성된 로또티켓을
 *  view로 보내준다
 */
package dto;

import model.Lotto;
import model.LottoTicket;

import java.util.List;

public class LottoTicketDto {
    private final LottoTicket lottoTicket;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public List<Lotto> getLottoTicket() {
        return lottoTicket.getLottoList();
    }
}

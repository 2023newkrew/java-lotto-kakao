package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;


public class LottoWinnerTicket {
    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public LottoWinnerTicket(LottoTicket lottoTicket, LottoNumber bonusBall) {
        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
        bonusAlreadyExistsCheck();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoTicket.getLottoNumbers();
    }

    public LottoNumber getBonusNumber() {
        return bonusBall;
    }

    private void bonusAlreadyExistsCheck() {
        if (this.lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList())
                .contains(this.bonusBall.getNumber())) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 이미 포함되어 있습니다.");
        }
    }}

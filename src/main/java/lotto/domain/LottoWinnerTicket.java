package lotto.domain;

import java.util.List;


public class LottoWinnerTicket {
    private final LottoTicket lottoTicket;
    private final int bonusBall;

    public LottoWinnerTicket(LottoTicket lottoTicket, int bonusBall) {
        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
        this.lottoTicket.rangeCheck(bonusBall);
        bonusAlreadyExistsCheck();
    }

    public List<Integer> getLottoNumbers() {
        return lottoTicket.getLottoNumbers();
    }

    public Integer getBonusNumber() {
        return bonusBall;
    }

    private void bonusAlreadyExistsCheck() {
        if (this.lottoTicket.getLottoNumbers().contains(this.bonusBall)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 이미 포함되어 있습니다.");
        }
    }}

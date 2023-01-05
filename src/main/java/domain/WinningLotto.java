package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {

    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if(winningLottoTicket.contains(bonusNumber)){
            throw new IllegalArgumentException("당첨 번호에 포함된 번호는 보너스 번호가 될 수 없습니다.");
        }

        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoMatchResult match(LottoTicket lottoTicket) {
        List<LottoNumber> unMatchLottoNumbers = lottoTicket.findUnMatchLottoNumbers(this.winningLottoTicket);
        List<LottoNumber> remainLottoNumbers = unMatchLottoNumbers.stream()
                .filter(lottoNumber -> (!lottoNumber.equals(bonusNumber)))
                .collect(Collectors.toList());

        int matchCount = this.winningLottoTicket.size() - unMatchLottoNumbers.size();
        boolean isBonusNumberMatched = (unMatchLottoNumbers.size() - remainLottoNumbers.size() != 0);
        return new LottoMatchResult(matchCount, isBonusNumberMatched);
    }
}

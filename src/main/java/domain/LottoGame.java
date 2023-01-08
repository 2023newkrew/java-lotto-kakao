package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private final List<LottoTicket> lottoTickets;
    private final WinningLotto winningLotto;

    public LottoGame(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        this.lottoTickets = lottoTickets;
        this.winningLotto = winningLotto;
    }

    private LottoMatchResult match(LottoTicket lottoTicket, WinningLotto winningLotto) {
        List<LottoNumber> unMatchLottoNumbers = lottoTicket.findUnMatchLottoNumbers(winningLotto.getWinningLottoTicket());
        List<LottoNumber> remainLottoNumbers = unMatchLottoNumbers.stream()
                .filter(lottoNumber -> (!lottoNumber.equals(winningLotto.getBonusNumber())))
                .collect(Collectors.toList());

        int matchCount = lottoTicket.size() - unMatchLottoNumbers.size();
        boolean isBonusNumberMatched = (unMatchLottoNumbers.size() - remainLottoNumbers.size() != 0);
        return new LottoMatchResult(matchCount, isBonusNumberMatched);
    }

    public GameResult play(){
        List<LottoMatchResult> lottoMatchResults = lottoTickets.stream()
                .map((lottoTicket) -> match(lottoTicket, winningLotto))
                .collect(Collectors.toList());

        return new GameResult(lottoMatchResults);
    }
}

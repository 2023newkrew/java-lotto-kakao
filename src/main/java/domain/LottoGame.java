package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private final List<LottoTicket> lottoTickets;
    private final WinningLotto winningLotto;

    public LottoGame(User user, WinningLotto lastWinningLotto) {
        this.lottoTickets = user.getLottoTickets();
        this.winningLotto = lastWinningLotto;
    }

    public LottoMatchResult match(LottoTicket lottoTicket) {
        List<LottoNumber> unMatchLottoNumbers = lottoTicket.findUnMatchLottoNumbers(winningLotto.getWinningLottoTicket());
        List<LottoNumber> remainLottoNumbers = unMatchLottoNumbers.stream()
                .filter(lottoNumber -> (!lottoNumber.equals(winningLotto.getBonusNumber())))
                .collect(Collectors.toList());

        int matchCount = winningLotto.getWinningLottoTicket().size() - unMatchLottoNumbers.size();
        boolean isBonusNumberMatched = (unMatchLottoNumbers.size() - remainLottoNumbers.size() != 0);
        return new LottoMatchResult(matchCount, isBonusNumberMatched);
    }

    public GameResult play(){
        List<LottoMatchResult> lottoMatchResults = lottoTickets.stream()
                .map(this::match)
                .collect(Collectors.toList());

        return new GameResult(lottoMatchResults);
    }
}

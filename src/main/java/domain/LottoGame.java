package domain;

import java.util.List;
import java.util.stream.Collectors;
import domain.LottoTicketStore.LottoTicket;

public class LottoGame {
    private final List<LottoTicket> lottoNumbers;
    private final WinningLotto winningLotto;

    public LottoGame(List<LottoTicket> lottoNumbers, WinningLotto winningLotto) {
        this.lottoNumbers = lottoNumbers;
        this.winningLotto = winningLotto;
    }

    private LottoMatchResult match(LottoNumbers lottoNumbers, WinningLotto winningLotto) {
        List<LottoNumber> unMatchLottoNumbers = lottoNumbers.findUnMatchLottoNumbers(winningLotto.getWinningLottoNumbers());
        List<LottoNumber> remainLottoNumbers = unMatchLottoNumbers.stream()
                .filter(lottoNumber -> (!lottoNumber.equals(winningLotto.getBonusNumber())))
                .toList();

        int matchCount = lottoNumbers.size() - unMatchLottoNumbers.size();
        boolean isBonusNumberMatched = (unMatchLottoNumbers.size() - remainLottoNumbers.size() != 0);
        return new LottoMatchResult(matchCount, isBonusNumberMatched);
    }

    public GameResult play(){
        List<LottoMatchResult> lottoMatchResults = lottoNumbers.stream()
                .map((lottoTicket) -> match(lottoTicket.getLottoNumbers(), winningLotto))
                .toList();

        return new GameResult(lottoMatchResults);
    }
}

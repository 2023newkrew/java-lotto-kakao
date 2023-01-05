package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class QuickPickSimulator implements LottoSimulator{
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketGenerator lottoTicketGenerator;

    public QuickPickSimulator(InputView inputView, OutputView outputView, LottoTicketGenerator lottoTicketGenerator){
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    @Override
    public void play() {
        List<LottoTicket> purchaseLottoTickets = getPurchaseLottoTickets();
        outputView.printLottoPurchaseInfo(purchaseLottoTickets);

        List<LottoMatchResult> lottoMatchResults = playLottoGames(purchaseLottoTickets);
        LottoMatchStatistics lottoMatchStatistics = new LottoMatchStatistics(lottoMatchResults);
        outputView.printLottoMatchStatistics(lottoMatchStatistics);
    }

    private List<LottoMatchResult> playLottoGames(List<LottoTicket> purchaseLottoTickets) {
        WinningLotto lastWinningLotto = inputView.getLastWinningLotto();

        return purchaseLottoTickets.stream()
                .map(lastWinningLotto::match)
                .collect(Collectors.toList());
    }

    private List<LottoTicket> getPurchaseLottoTickets() {
        int purchasePrice = inputView.getPurchasePrice();
        int lottoCount =  purchasePrice / LottoConstant.LOTTO_PRICE;

        return lottoTicketGenerator.generate(lottoCount);
    }
}

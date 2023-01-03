package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoGame;
import domain.lotto.WinningNumbers;
import domain.lotto.number.LottoNumberMaker;
import domain.lotto.number.RandomNumberGenerator;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        LottoTickets lottoTickets = createLottoTickets();
        OutputView.printLottoTickets(lottoTickets);
        LottoGame lottoGame = new LottoGame(lottoTickets, createWinningNumbers());
        LottoResults lottoResults = lottoGame.getLottoTicketsResult();
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTickets createLottoTickets() {
        Integer purchaseAmount = InputView.inputPurchaseAmount();
        LottoNumberMaker lottoNumberMaker = new LottoNumberMaker();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(new LottoTicket(lottoNumberMaker.makeNumbers(randomNumberGenerator)));
        }
        return new LottoTickets(lottoTicketList);
    }

    private WinningNumbers createWinningNumbers() {
        WinningNumbersDto winningNumbersDto = InputView.inputWinningNumbers();
        return new WinningNumbers(winningNumbersDto.getLottoNumbers(), winningNumbersDto.getBonusNumber());
    }
}

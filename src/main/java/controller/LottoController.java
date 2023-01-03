package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoGame;
import domain.lotto.WinningNumbers;
import domain.lotto.makingnumbers.LottoNumberMaker;
import domain.lotto.makingnumbers.RandomNumberGenerator;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTicketList;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        LottoTicketList lottoTicketList = createLottoTickets();
        OutputView.printLottoTickets(lottoTicketList);
        LottoGame lottoGame = new LottoGame(lottoTicketList, createWinningNumbers());
        LottoResults lottoResults = lottoGame.getLottoTicketsResult();
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTicketList createLottoTickets() {
        Integer purchaseAmount = InputView.inputPurchaseAmount();
        LottoNumberMaker lottoNumberMaker = new LottoNumberMaker();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(new LottoTicket(lottoNumberMaker.makeNumbers(randomNumberGenerator)));
        }
        return new LottoTicketList(lottoTicketList);
    }

    private WinningNumbers createWinningNumbers() {
        WinningNumbersDto winningNumbersDto = InputView.inputWinningNumbers();
        return new WinningNumbers(winningNumbersDto.getLottoNumbers(), winningNumbersDto.getBonusNumber());
    }
}

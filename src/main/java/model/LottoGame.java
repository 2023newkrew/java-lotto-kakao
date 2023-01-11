/**
 * 로또 게임 전체를 진행하는 클래스 (컨트롤러와 연결됨)
 */
package model;

import dto.LottoResultDto;
import dto.LottoTicketDto;
import dto.LottoWinnerDto;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private LottoTicket lottoTicket; //로또 티켓 관리 (수동+자동)
    private LottoWinner lottoWinner; //당첨 로또 관리(사용자 입력받음)

    private int lottoCount; //전체 로또 개수 = purchaseMoney/1000;

    private int manualLottoCount; // 수동 로또 개수 = 입력 받는 값임

    private int autoLottoCount; // 자동 로또 개수 = 전체 - 수동
    private Money purchaseMoney;

    public void setPurchaseMoney(Money purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public int getTotalLottoCount() {
        return (int) purchaseMoney.getPurchaseMoney() / LOTTO_PRICE;
    }

    public void setLottoTicket(List<Lotto> manualLottos) {
        lottoTicket = LottoFactory.createLottoTicket(getTotalLottoCount() - manualLottos.size(), manualLottos);
    }

    public void setLottoWinner(LottoWinnerDto lottoWinnerDto) {
        lottoWinner = lottoWinnerDto.getLottoWinner();
    }

    public LottoTicketDto getLottoTicket() {
        return new LottoTicketDto(lottoTicket);
    }

    public LottoResultDto getResult() {
        Banker banker = new Banker();
        LottoResult lottoResult = new LottoResult(lottoWinner, lottoTicket);
        long winningMoney = banker.getTotalPrizeMoney(lottoResult);
        return new LottoResultDto(lottoResult.getLottoPlaces(), (double) winningMoney / purchaseMoney.getPurchaseMoney());
    }

}

/**
 * 로또 게임 전체를 진행하는 클래스 (컨트롤러와 연결됨)
 */
package model;

import dto.LottoResultDto;
import dto.LottoTicketDto;
import dto.LottoWinnerDto;

public class LottoGame {
    private LottoTicket lottoTicket; //로또 티켓 관리
    private LottoWinner lottoWinner; //당첨 로또 관리(사용자 입력받음)

    private long purchaseMoney;

    public void setLottoTicket(long purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
        lottoTicket = LottoFactory.createLottoTicket(purchaseMoney);
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
        return new LottoResultDto(lottoResult.getLottoPlaces(), (double)winningMoney/purchaseMoney);

    }

}

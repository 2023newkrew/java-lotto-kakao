/**
 * 로또 게임 전체를 진행하는 클래스 (컨트롤러와 연결됨)
 */
package model;

import dto.LottoResultDto;
import dto.LottoTicketDto;
import dto.LottoWinnerDto;
import dto.ManualLottoDto;
import exception.ManualLottoCountException;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private LottoTicket lottoTicket; //로또 티켓 관리 (수동+자동)
    private LottoWinner lottoWinner; //당첨 로또 관리(사용자 입력받음)
    private PurchaseMoney purchaseMoney; //구매 금액

    public void setPurchaseMoney(PurchaseMoney purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public int getTotalLottoCount() {
        return (int) purchaseMoney.getMoney() / LOTTO_PRICE;
    }

    /**
     * @throws ManualLottoCountException - 입력된 수동 로또의 개수가 0 미만이거나 전체 구입 개수를 초과할 때 발생하는 예외
     */
    public int setManualLottoCount(int manualLottoCount) throws ManualLottoCountException {
        if (manualLottoCount < 0 || manualLottoCount > getTotalLottoCount()) {
            throw new ManualLottoCountException();
        }
        return manualLottoCount;
    }

    public void setLottoTicket(ManualLottoDto manualLottos) {
        lottoTicket = LottoFactory.createLottoTicket(getTotalLottoCount() - manualLottos.getLottos().size(), manualLottos.getLottos());
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
        Money winningMoney = banker.getTotalPrizeMoney(lottoResult);
        return new LottoResultDto(lottoResult.getLottoPlaces(), winningMoney.rate(purchaseMoney));
    }

}

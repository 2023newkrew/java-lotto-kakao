package domain;

public class WinningLotto {

    public static final String NUMBER_INCLUDED_IN_WINNING_LOTTO_TICKET_CANNOT_BE_BONUS_NUMBER_MSG = "당첨 번호에 포함된 번호는 보너스 번호가 될 수 없습니다.";
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if(winningLottoTicket.contains(bonusNumber)){
            throw new IllegalArgumentException(NUMBER_INCLUDED_IN_WINNING_LOTTO_TICKET_CANNOT_BE_BONUS_NUMBER_MSG);
        }

        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoTicket getWinningLottoTicket() {
        return winningLottoTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}

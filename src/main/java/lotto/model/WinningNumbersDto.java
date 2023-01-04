package lotto.model;

public class WinningNumbersDto {
    private final LottoTicketDto lottoTicketDto;
    private final int bonusNumber;

    public WinningNumbersDto(LottoTicketDto lottoTicketDto, int bonusNumber) {
        this.lottoTicketDto = lottoTicketDto;
        this.bonusNumber = bonusNumber;
    }

    public LottoTicketDto getLottoTicketDto() {
        return lottoTicketDto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

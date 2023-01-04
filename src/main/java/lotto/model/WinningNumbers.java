package lotto.model;

public class WinningNumbers {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public WinningNumbers(WinningNumbersDto winningNumbersDto) {
        this.winningTicket = new LottoTicket(winningNumbersDto.getLottoTicketDto());
        this.bonusNumber = LottoNumber.valueOf(winningNumbersDto.getBonusNumber());
    }

    public Grade match(LottoTicket lottoTicket) {
        int matchedCount = (int) winningTicket.stream()
                .filter(lottoTicket::contains)
                .count();

        int bonusCount = 0;
        if (matchedCount == 5 && lottoTicket.contains(bonusNumber)) {
            bonusCount++;
        }

        return Grade.getGrade(matchedCount + 10 * bonusCount);
    }
}

package lotto.model;

public class WinningNumbers {
    private static final int WEIGHT = 10;
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validateDistinction(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateDistinction(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 당첨 번호와 중복되지 않아야 합니다");
        }
    }

    public Grade match(LottoTicket lottoTicket) {
        int matchedCount = winningTicket.match(lottoTicket);
        int bonusCount = 0;
        if (matchedCount == 5 && lottoTicket.contains(bonusNumber)) {
            bonusCount++;
        }
        return Grade.getGrade(matchedCount + WEIGHT * bonusCount);
    }
}

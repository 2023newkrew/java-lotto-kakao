package lotto.model.prize;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

public class WinningNumbers {
    private final LottoTicket winning;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winning, LottoNumber bonusNumber) {
        this.winning = winning;
        if (!this.isValidBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되지 않아야 합니다.");
        }
        this.bonusNumber = bonusNumber;
    }

    private boolean isValidBonusNumber(LottoNumber bonusNumber) {
        return !this.winning.contains(bonusNumber);
    }

    public int matchNumbers(LottoTicket ticket) {
        int matchCount = 0;
        for (LottoNumber number : ticket.getNumbers()) {
            matchCount += this.count(number);
        }
        return matchCount;
    }

    public boolean hasBonus(LottoTicket ticket) {
        return ticket.contains(this.bonusNumber);
    }

    private boolean isMatched(LottoNumber number) {
        return this.winning.contains(number);
    }

    private int count(LottoNumber number) {
        if (this.isMatched(number)) {
            return 1;
        }
        return 0;
    }
}

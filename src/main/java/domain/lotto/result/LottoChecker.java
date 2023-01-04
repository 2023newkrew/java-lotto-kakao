package domain.lotto.result;

import domain.lotto.number.WinningNumbers;
import domain.lotto.ticket.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;

public class LottoChecker {

    public static LottoResultType getResult(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
        List<Integer> containedNumbers = lottoTicket.getLottoNumbers().stream()
                .filter((number) -> winningNumbers.getLottoNumber().contains(number))
                .collect(Collectors.toList());

        return getRank(containedNumbers.size(),
                lottoTicket.getLottoNumbers().contains(winningNumbers.getBonusNumber()));
    }

    private static LottoResultType getRank(final Integer size, final Boolean hasBonusNumber) {
        if (size < 3) return LottoResultType.FAIL;
        if (size == 3) return LottoResultType.FIFTH_PLACE;
        if (size == 4) return LottoResultType.FOURTH_PLACE;
        if (hasBonusNumber) return LottoResultType.SECOND_PLACE;
        if (size == 5) return LottoResultType.THIRD_PLACE;
        return LottoResultType.FIRST_PLACE;
    }
}

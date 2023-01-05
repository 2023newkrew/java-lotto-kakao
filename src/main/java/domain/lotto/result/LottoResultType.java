package domain.lotto.result;

import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;

import java.util.List;
import java.util.stream.Collectors;

public enum LottoResultType {

    FAIL(0, "꽝 (3개 미만) (0원)"),
    FIFTH_PLACE(5_000, "3개 일치 (5000원)"),
    FOURTH_PLACE(50_000, "4개 일치 (50000원)"),
    THIRD_PLACE(1_500_000, "5개 일치 (1500000원)"),
    SECOND_PLACE(30_000_000, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST_PLACE(2_000_000_000, "6개 일치 (2000000000원)");

    private final int prize;
    private final String message;

    LottoResultType(final int prize, final String message) {
        this.prize = prize;
        this.message = message;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }

    public static LottoResultType getLottoResult(final LottoNumbers lottoNumbers, final WinningNumbers winningNumbers) {
        List<Integer> containedNumbers = lottoNumbers.getNumbers().stream()
                .filter((number) -> winningNumbers.getLottoNumber().contains(number))
                .collect(Collectors.toList());

        return getRank(containedNumbers.size(),
                lottoNumbers.getNumbers().contains(winningNumbers.getBonusNumber()));
    }

    private static LottoResultType getRank(final int size, final boolean hasBonusNumber) {
        if (size < 3) return LottoResultType.FAIL;
        if (size == 3) return LottoResultType.FIFTH_PLACE;
        if (size == 4) return LottoResultType.FOURTH_PLACE;
        if (hasBonusNumber) return LottoResultType.SECOND_PLACE;
        if (size == 5) return LottoResultType.THIRD_PLACE;
        return LottoResultType.FIRST_PLACE;
    }
}

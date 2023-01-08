package domain.lotto.result;

import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;

import java.util.List;
import java.util.stream.Collectors;

public enum LottoResultType {

    FAIL(-1, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    SECOND_PLACE(5, true, 30_000_000),
    THIRD_PLACE(5, false, 1_500_000),
    FIRST_PLACE(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean needBonusNumber;
    private final int prize;

    public int getPrize() {
        return prize;
    }

    LottoResultType(final int matchCount, final boolean needBonusNumber, final int prize) {
        this.matchCount = matchCount;
        this.needBonusNumber = needBonusNumber;
        this.prize = prize;
    }

    public static LottoResultType getLottoResult(final LottoNumbers lottoNumbers, final WinningNumbers winningNumbers) {
        List<Integer> containedNumbers = lottoNumbers.getNumbers().stream()
                .filter((number) -> winningNumbers.getLottoNumber().contains(number))
                .collect(Collectors.toList());

        return getRank(containedNumbers.size(),
                lottoNumbers.getNumbers().contains(winningNumbers.getBonusNumber()));
    }

    private static LottoResultType getRank(final int matchNumber, final boolean hasBonusNumber) {
        for (LottoResultType type : values()) {
            if (type.checkMatch(matchNumber, hasBonusNumber)) return type;
        }
        return FAIL;
    }

    public boolean checkMatch(int matchCount, boolean hasBonusNumber) {
        if(needBonusNumber && !hasBonusNumber) {
            return false;
        }
        return this.matchCount == matchCount;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        if (matchCount < 3) {
            sb.append("꽝! 3개 미만");
        }
        if (matchCount >= 3) {
            sb.append(matchCount).append("개 일치");
        }
        if (needBonusNumber) {
            sb.append(", 보너스 볼 일치");
        }
        return sb.append("(").append(prize).append("원)").toString();
    }
}

package lotto.model.ranking;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.SingleLottoNumber;

import java.util.Objects;

public class WinningNumber {

    private final LottoNumber winningNumber;

    private final SingleLottoNumber bonus;

    public static WinningNumber from(LottoNumber winningNumber, SingleLottoNumber bonus) {
        validate(winningNumber, bonus);

        return new WinningNumber(winningNumber, bonus);
    }

    private static void validate(LottoNumber winningNumber, SingleLottoNumber bonus) {
        if (Objects.isNull(winningNumber)) {
            throw new IllegalArgumentException("당첨 번호가 없습니다.");
        }
        if (Objects.isNull(bonus)) {
            throw new IllegalArgumentException("추가 번호가 없습니다.");
        }
        if (winningNumber.hasNumber(bonus)) {
            throw new IllegalArgumentException("중복된 당첨 번호는 생성할 수 없습니다.");
        }
    }

    private WinningNumber(LottoNumber winningNumber, SingleLottoNumber bonus) {
        this.winningNumber = winningNumber;
        this.bonus = bonus;
    }

    public LottoRanking judge(LottoNumber lotto){
        int commonNumberCount = winningNumber.countCommonNumber(lotto);
        boolean hasBonus = lotto.hasNumber(bonus);

        return LottoRanking.from(commonNumberCount, hasBonus);
    }

    @Override
    public String toString() {
        return "{" + winningNumber +
                ", " + bonus +
                '}';
    }
}

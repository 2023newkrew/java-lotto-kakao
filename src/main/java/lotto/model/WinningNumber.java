package lotto.model;

import lotto.model.lotto.Lotto;
import lotto.model.prize.Prize;
import lotto.model.vo.LottoNumber;

public class WinningNumber {

    private final Lotto answer;

    private final LottoNumber bonus;


    public static WinningNumber from(Lotto answer, LottoNumber bonus) {
        if (answer.hasNumber(bonus)) {
            throw new IllegalArgumentException("중복된 당첨 번호는 생성할 수 없습니다.");
        }

        return new WinningNumber(answer, bonus);
    }

    public WinningNumber(Lotto answer, LottoNumber bonus) {
        this.answer = answer;
        this.bonus = bonus;
    }

    public Prize judge(Lotto lotto) {
        long commonNumberCount = answer.countCommonNumber(lotto);
        boolean hasBonus = lotto.hasNumber(bonus);

        return Prize.from(commonNumberCount, hasBonus);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", answer.toString(), bonus.toString());
    }
}

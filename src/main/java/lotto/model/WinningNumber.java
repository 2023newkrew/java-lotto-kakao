package lotto.model;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

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

    public PrizeMap judge(LottoBundle lottoBundle) {
        List<Prize> prizes = lottoBundle.stream()
                .map(this::judge)
                .collect(Collectors.toList());

        return PrizeMap.from(prizes);
    }

    private Prize judge(Lotto lotto) {
        long commonNumberCount = answer.countCommonNumber(lotto);
        boolean hasBonus = lotto.hasNumber(bonus);
        return Prize.from(commonNumberCount, hasBonus);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", answer.toString(), bonus.toString());
    }
}

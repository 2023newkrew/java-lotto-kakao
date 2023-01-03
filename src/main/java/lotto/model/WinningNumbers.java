package lotto.model;

public class WinningNumbers {

    private final Lotto lotto;
    private final LottoNumber bonus;

    public WinningNumbers(Lotto lotto, LottoNumber bonus) {
        if(lotto.hasNumber(bonus)){
            throw new IllegalArgumentException("중복된 당첨 번호는 생성할 수 없습니다.");
        }

        this.lotto = lotto;
        this.bonus = bonus;
    }

    public Prize judge(Lotto other) {
        long overlappedNumberCount = lotto.countOverlappedNumber(other);
        boolean hasBonus = other.hasNumber(bonus);
        return Prize.from(overlappedNumberCount, hasBonus);
    }
}

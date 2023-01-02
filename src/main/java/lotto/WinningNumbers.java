package lotto;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonus;

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonus) {
        if(lottoNumbers.hasNumber(bonus)){
            throw new IllegalArgumentException("중복된 당첨 번호는 생성할 수 없습니다.");
        }

        this.lottoNumbers = lottoNumbers;
        this.bonus = bonus;
    }

    public Prize judge(LottoNumbers other) {
        long overlappedNumberCount = lottoNumbers.countOverlappedNumber(other);
        boolean hasBonus = other.hasNumber(bonus);
        return Prize.valueOf(overlappedNumberCount, hasBonus);
    }
}

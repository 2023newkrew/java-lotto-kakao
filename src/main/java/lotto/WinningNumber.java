package lotto;

public class WinningNumber {

    private final LottoNumber winningNumber;
    private final int bonus;

    public WinningNumber(LottoNumber winningNumber, int bonus) {
        this.winningNumber = winningNumber;
        this.bonus = bonus;
    }

    public Prize judge(LottoNumber lottoNumber) {
        long overlappedNumberCount = winningNumber.countOverlappedNumber(lottoNumber);
        boolean hasBonus = lottoNumber.hasNumber(bonus);
        return Prize.valueOf(overlappedNumberCount, hasBonus);
    }
}

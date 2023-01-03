package lotto;

public class LotteryNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public LotteryNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new RuntimeException("잘못 된 로또 번호");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryNumber)) return false;

        LotteryNumber cp = (LotteryNumber) obj;

        return this.number == cp.number;
    }
}

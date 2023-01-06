package lotto.model.ticket;

import java.util.HashMap;

public class LottoNumber {
    public static final int NUMBER_RANGE = 45;
    private static final HashMap<Integer, LottoNumber> NUMBERS = new HashMap<>();

    static {
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            LottoNumber.NUMBERS.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber get(int number) {
        if (LottoNumber.isInvalidNumber(number)) {
            throw new IllegalArgumentException("로또 번호는 1에서 45 사이의 정수여야 합니다.");
        }
        return LottoNumber.NUMBERS.get(number);
    }

    public static LottoNumber get(String stringNumber) {
        int number = Integer.parseInt(stringNumber);
        if (LottoNumber.isInvalidNumber(number)) {
            throw new IllegalArgumentException("로또 번호는 1에서 45 사이의 정수여야 합니다.");
        }
        return LottoNumber.NUMBERS.get(number);
    }

    public int getNumber() {
        return this.number;
    }

    private static boolean isInvalidNumber(int number) {
        return number <= 0 || number > LottoNumber.NUMBER_RANGE;
    }
}

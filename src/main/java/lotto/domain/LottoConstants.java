package lotto.domain;

public final class LottoConstants {

    private LottoConstants() {
    }

    public static final long LOTTO_PRICE = 1000;

    public static final int MIN_LOTTO_SIZE = 0;

    public static final int LOTTO_SIZE = 6;

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    public static String STATICS_FORMAT(double statics){
        return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", statics);
    }
}

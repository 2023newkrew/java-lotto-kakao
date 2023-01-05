package lotto.domain.constants;

/**
 * This Class contains widely being used constants among lotto package.
 * WARNING : DO NOT CHANGE THIS PARAMETER CARELESSLY. CHANGES ON THESE PARAMETER CAN AFFECT WHOLE PROGRAM.
 */
public final class LottoConstants {
    /**
     * LOTTO_PRICE : indicates how much is the one lotto.
     */
    public final static long LOTTO_PRICE = 1000L;
    /**
     * BALLNUMBER_MIN_VALUE : indicates what is the minimum number of lotto_ball_number.
     */
    public static final int BALLNUMBER_MIN_VALUE = 1;
    /**
     * BALLNUMBER_MAX_VALUE : indicates what is the maximum number of lotto_ball_number.
     */
    public static final int BALLNUMBER_MAX_VALUE = 45;
    /**
     * ONE_TRIAL_BALL_COUNT : indicates how many balls in one lotto trial.
     */
    public static final int ONE_TRIAL_BALL_COUNT = 6;

    /**
     * indicates cash if you win first prize in this game.
     */
    public static final long FIRST_PRIZE_CASH = 2_000_000_000L;


    /**
     * indicates cash if you win second prize in this game.
     */
    public static final long SECOND_PRIZE_CASH = 30_000_000L;

    /**
     * indicates cash if you win third prize in this game.
     */
    public static final long THIRD_PRIZE_CASH = 1_500_000L;
    /**
     * indicates cash if you win fourth prize in this game.
     */
    public static final long FOURTH_PRIZE_CASH = 50_000L;
    /**
     * indicates cash if you win fifth prize in this game.
     */
    public static final long FIFTH_PRIZE_CASH = 5_000L;

    /**
     * This class is for containing constants, not for making instances.
     */
    private LottoConstants(){} // 객체 생성 용도가 아님을 명시
}

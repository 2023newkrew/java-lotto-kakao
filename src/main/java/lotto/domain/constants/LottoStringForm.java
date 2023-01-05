package lotto.domain.constants;

/**
 * This Class contains widely being used String formats among lotto package, especially in domain package.
 * WARNING : DO NOT CHANGE THESE FORMATS CARELESSLY. CHANGES ON THESE PARAMETER CAN AFFECT WHOLE PROGRAM.
 */
public class LottoStringForm {
    public static class Korean{
        public static final String CASH_UNIT = "원";
        public static final String COUNT_UNIT = "장";

        /**
         * format : (number of MATCHES)개 일치
         */
        public static final String LOTTO_RESULT_FORM = "%d개 일치";
        /**
         * This is for when bonus ball is matched.
         */
        public static final String LOTTO_RESULT_BONUS_FORM = ", 보너스 볼 일치";

        /**
         * Starting sentences of TotalResult.toString()
         */
        public static final String TOTAL_RESULT_STARTING = "당첨 통계\n==============\n";

        /**
         *
         * format : 총 수익률은 {@code SURPLUS_RATIO}입니다. 기준이 1이기 때문에 결과적으로 ({@code TOTAL_RESULT_BENEFIT} if benefit or {@code TOTAL_RESULT_LOSS} if loss) 라는 의미임
         */
        public static final String TOTAL_RESULT_RATIO = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n";
        public static final String TOTAL_RESULT_BENEFIT = "이득";
        public static final String TOTAL_RESULT_LOSS = "손해";
    }

    /**
     * This class is for containing constants, not for making instances.
     */
    private LottoStringForm(){} // 객체 생성 용도가 아님을 명시
}

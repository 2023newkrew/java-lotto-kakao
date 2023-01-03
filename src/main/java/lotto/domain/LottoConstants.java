package lotto.domain;

import static lotto.domain.LottoRank.*;

public final class LottoConstants {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_MIN_COUNT = 0;
    public static final long LOTTO_PRICE = 1000;


    public static String getString(LottoRank lottoRank, int count) {
        if (lottoRank.isRequiredBonus()) {
            return hasBonusString(lottoRank, count);
        }
        if(lottoRank == SIXTH){
            return "";
        }
        return String.format("%d개 일치 (%d원) - %d개\n", lottoRank.getMatchCount(), lottoRank.getWinning(),
                count);
    }

    public static String hasBonusString(LottoRank lottoRank, int count) {

        return String.format(("5개 일치, 보너스 볼 일치 (%d원) - %d개\n"), lottoRank.getWinning(), count);
    }


}

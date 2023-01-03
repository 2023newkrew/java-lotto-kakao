package lotto.domain;

import static lotto.domain.LottoRank.SIXTH;

public final class LottoResultMessage {
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

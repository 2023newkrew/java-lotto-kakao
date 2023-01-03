package lotto.domain;

public final class LottoConstants {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_MIN_COUNT = 0;
    public static final long LOTTO_PRICE = 1000;

    public static String getString(LottoRank lottoRank, int count){
        if(lottoRank == LottoRank.FIRST){
            return String.format("6개 일치 (%d원) - %d개\n", lottoRank.winning(), count);
        }
        if(lottoRank == LottoRank.SECOND){
            return hasBonusString(lottoRank, count);
        }
        return String.format("%d개 일치 (%d원)- %d개\n", 7-lottoRank.index(), lottoRank.winning(), count);

    }

    public static String hasBonusString(LottoRank lottoRank, int count){

        return String.format(("5개 일치, 보너스 볼 일치(%d원) - %d개\n"), lottoRank.winning(), count);
    }




}

package lotto.model;

public enum LottoRank {
    RANK1(5000),
    RANK2(50000),
    RANK3(1500000),
    RANK4(30000000),
    RANK5(2000000000),
    RANK6(0);

    private final Integer reward;

    LottoRank(Integer reward){
        this.reward = reward;
    }

    public static LottoRank fromCountAndBonus(Integer count, boolean bonus){
        if(count == 6) return RANK1;
        if(count == 5 && bonus) return RANK2;
        if(count == 5) return RANK3;
        if(count == 4) return RANK4;
        if(count == 3) return RANK5;
        return RANK6;
    }
}

package lotto;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class LottoTrial {
    protected final List<LottoBallNumber> ballNumbers = new ArrayList<>();
    public List<LottoBallNumber> getBallNumbers(){
        return ballNumbers;
    }
    @Override
    public String toString() {
        String result = "[";
        for (int i=0;i<LottoConstants.BALLCOUNT_LIMIT-1;i++){
            result += ballNumbers.get(i) + ", ";
        }
        result += ballNumbers.get(LottoConstants.BALLCOUNT_LIMIT-1) + "]";
        return result;
    }

    protected void check(LottoTrial lottoTrial){
        Set<LottoBallNumber> lottoSet = new HashSet<>();
        for (LottoBallNumber bn : ballNumbers){
            lottoSet.add(bn);
        }
        if (lottoSet.size()!=LottoConstants.BALLCOUNT_LIMIT) {
            throw new DuplicatedBallNumber();
        }
    }
    protected void sort(LottoTrial lottoTrial){
        Collections.sort(lottoTrial.ballNumbers);
    }
}

/*
public class LottoTrial {
    private final List<LottoBallNumber> lottoTrial;

    private LottoTrial(Builder builder){ // 랜덤

    }
    public static class Builder{
        List<LottoBallNumber> tempLottoTrial;

        public LottoTrial build(){
            return new LottoTrial(this);
        }
    }
}
*/
package lotto;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class LottoTrial {
    protected final List<LottoBallNumber> ballNumbers = new ArrayList<>();
    void check(LottoTrial lottoTrial){
        Set<LottoBallNumber> lottoSet = new HashSet<>();
        for (LottoBallNumber bn : ballNumbers){
            lottoSet.add(bn);
        }
        if (lottoSet.size()!=6) {
            throw new DuplicatedBallNumber();
        }
    }
    void sort(LottoTrial lottoTrial){
        Collections.sort(lottoTrial.ballNumbers);
    }
    public List<LottoBallNumber> getBallNumbers(){
        return ballNumbers;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i=0;i<5;i++){
            result += ballNumbers.get(i) + ", ";
        }
        result += ballNumbers.get(5) + "]";
        return result;
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
package lotto;

import lotto.exception.DuplicatedBallNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<LottoBallNumber> getBallNumbers(){
        return ballNumbers;
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
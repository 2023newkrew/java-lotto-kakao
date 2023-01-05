package lotto.domain;

import lotto.domain.exception.DuplicatedBallNumber;
import lotto.domain.exception.InvalidLottoTrial;
import static lotto.domain.constants.LottoConstants.*;

import java.util.*;

public abstract class LottoTrial {
    protected final List<LottoBallNumber> ballNumbers = new ArrayList<>();
    protected LottoTrial(){}

    public List<LottoBallNumber> getBallNumbers(){
        return ballNumbers;
    }
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i< ONE_TRIAL_BALL_COUNT -1; i++){
            result += ballNumbers.get(i) + ", ";
        }
        result += ballNumbers.get(ONE_TRIAL_BALL_COUNT -1) + "]";
        return result;
    }

    protected void validate(LottoTrial lottoTrial){
        validateLottoCount(lottoTrial);
        validateDuplication(lottoTrial);
    }
    private void validateLottoCount(LottoTrial lottoTrial){
        if (lottoTrial.getBallNumbers().size()!= ONE_TRIAL_BALL_COUNT){
            throw new InvalidLottoTrial();
        }
    }

    private void validateDuplication(LottoTrial lottoTrial){
        Set<LottoBallNumber> lottoSet = new HashSet<>();
        lottoSet.addAll(lottoTrial.ballNumbers);
        if (lottoSet.size()!= ONE_TRIAL_BALL_COUNT) {
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
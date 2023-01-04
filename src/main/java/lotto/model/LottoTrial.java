package lotto.model;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class LottoTrial {
    protected final List<LottoBallNumber> ballNumbers = new ArrayList<>();

    void check(){
        Set<LottoBallNumber> lottoSet = new HashSet<>(this.ballNumbers);

        if (lottoSet.size()!=LottoConstants.BALLCOUNT_LIMIT) {
            throw new DuplicatedBallNumber();
        }
    }

    void sort(){
        Collections.sort(this.ballNumbers);
    }

    public List<LottoBallNumber> getBallNumbers(){
        return ballNumbers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT - 1; i++){
            result.append(ballNumbers.get(i)).append(", ");
        }

        result.append(ballNumbers.get(LottoConstants.BALLCOUNT_LIMIT - 1)).append("]");

        return result.toString();
    }
}
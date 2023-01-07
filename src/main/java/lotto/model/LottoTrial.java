package lotto.model;

import lotto.exception.DuplicatedBallNumber;

import java.util.*;

public abstract class LottoTrial {
    protected final List<LottoBall> ballNumbers = new ArrayList<>();

    void check() {
        Set<LottoBall> lottoSet = new HashSet<>(this.ballNumbers);

        if (lottoSet.size() != LottoConstants.BALLCOUNT_LIMIT) {
            throw new DuplicatedBallNumber();
        }
    }

    void sort() {
        Collections.sort(this.ballNumbers);
    }

    public List<LottoBall> getBallNumbers(){
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
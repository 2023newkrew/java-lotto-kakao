package lotto.domain.prizestrategy;

import lotto.domain.Lotto;

public class FirstPrizeStrategy implements PrizeStrategy {

    @Override
    public boolean isQualified(int matchNumberCount, boolean hasMagicNumber) {
        return matchNumberCount == Lotto.LOTTO_NUMBER_LENGTH;
    }
}

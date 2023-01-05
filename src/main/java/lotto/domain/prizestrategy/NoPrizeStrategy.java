package lotto.domain.prizestrategy;

import lotto.domain.Lotto;

public class NoPrizeStrategy implements PrizeStrategy {

    @Override
    public boolean isQualified(int matchNumberCount, boolean hasMagicNumber) {
        return matchNumberCount < Lotto.LOTTO_NUMBER_LENGTH - 3;
    }
}

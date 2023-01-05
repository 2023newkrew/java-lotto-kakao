package lotto.domain.prizestrategy;

import lotto.domain.constant.LottoRule;

public class FirstPrizeStrategy implements PrizeStrategy {

    @Override
    public boolean isQualified(int matchNumberCount, boolean hasMagicNumber) {
        return matchNumberCount == LottoRule.LENGTH;
    }
}

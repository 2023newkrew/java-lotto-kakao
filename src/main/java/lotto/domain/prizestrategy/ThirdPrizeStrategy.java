package lotto.domain.prizestrategy;

import lotto.domain.constant.LottoRule;

public class ThirdPrizeStrategy implements PrizeStrategy {

    @Override
    public boolean isQualified(int matchNumberCount, boolean hasMagicNumber) {
        return matchNumberCount == LottoRule.LENGTH - 1 && !hasMagicNumber;
    }
}

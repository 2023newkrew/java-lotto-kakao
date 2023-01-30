package lotto.domain.prizestrategy;

@FunctionalInterface
public interface PrizeStrategy {

    boolean isQualified(int matchNumberCount, boolean hasMagicNumber);
}

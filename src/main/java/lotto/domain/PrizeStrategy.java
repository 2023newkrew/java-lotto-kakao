package lotto.domain;

@FunctionalInterface
interface PrizeStrategy {

    boolean isQualified(int matchNumberCount, boolean hasMagicNumber);
}

package lotto.domain;

@FunctionalInterface
interface PrizeStrategy {

    boolean isWon(int matchNumberCount, boolean hasMagicNumber);
}

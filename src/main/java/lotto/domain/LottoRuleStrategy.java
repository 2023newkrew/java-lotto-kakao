package lotto.domain;

@FunctionalInterface
interface LottoRuleStrategy {

    boolean isWon(int matchNumberCount, boolean hasMagicNumber);
}

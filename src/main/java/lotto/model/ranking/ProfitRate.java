package lotto.model.ranking;

import lotto.model.store.LottoReceipt;
import lotto.model.store.Money;

import java.util.Objects;

public class ProfitRate {

    private final double profitRate;

    public static ProfitRate from(LottoReceipt receipt, Money totalPrize) {
        if (Objects.isNull(receipt)) {
            throw new IllegalArgumentException("영수증이 없습니다.");
        }
        double profitRate = calculateProfitRate(receipt, totalPrize);

        return new ProfitRate(profitRate);
    }

    private static double calculateProfitRate(LottoReceipt receipt, Money totalPrize) {
        Money payment = receipt.getPayment();
        Money change = receipt.getChange();
        Money totalProfit = change.add(totalPrize);

        return totalProfit.divide(payment, 2);
    }

    private ProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public double doubleValue() {
        return profitRate;
    }
}

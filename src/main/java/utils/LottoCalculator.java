package utils;

import domain.Payment;
import domain.Rank;

import java.util.Map;

import static constant.LottoSetting.LOTTO_PRICE;

public class LottoCalculator {

    public static int calculateNumberOfLotto(Payment payment) {
        return payment.getPayment() / LOTTO_PRICE;
    }

    public static double calculateYield(Payment payment, Map<Rank, Integer> rankMap) {
        long sum = rankMap.keySet()
                .stream()
                .mapToLong(rank -> rank.getPrize() * rankMap.get(rank))
                .sum();

        return Math.floor(((double) sum / payment.getPayment()) * 100) / 100;
    }
}

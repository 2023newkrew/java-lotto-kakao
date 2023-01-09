package domain;

import dto.LottoResult;
import dto.PurchasedLotto;
import dto.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoBuyer {
    private static final Integer LOTTO_COST = 1000;
    private static final Integer RATE_DECIMAL_POINT = 100;

    private Integer money;
    private List<Lotto> lottos = new ArrayList<>();

    public LottoBuyer(Integer money) {
        this.money = money;
    }
    
    public PurchasedLotto buyFrom(LottoPurchasePlace lottoPurchasePlace, List<Lotto> manualLottos) {
       PurchasedLotto purchased = lottoPurchasePlace.purchase(money, manualLottos);
        this.lottos.addAll(purchased.getManual());
        this.lottos.addAll(purchased.getAuto());
        return purchased;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> statuses = lottos.stream()
                .map(winningLotto::getRank)
                .collect(Collectors.toList());

        Map<LottoRank, Integer> map = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            map.put(lottoRank, Collections.frequency(statuses, lottoRank));
        }

        Double earningRate =  calculateEarningRate(map);
        return new LottoResult(map, earningRate);
    }

    public Double calculateEarningRate(Map<LottoRank, Integer> map) {
        Long earningMoney = map.entrySet()
            .stream()
            .mapToLong(entry -> entry.getValue() * entry.getKey().getReward())
            .reduce(0, Long::sum);

        Integer investMoney = (money / LOTTO_COST) * LOTTO_COST;
        Double rate = ((earningMoney - investMoney) / (double) investMoney) * 100;
        if (investMoney == 0) {
            return 0.00d;
        }
        return Math.floor(rate * RATE_DECIMAL_POINT) / RATE_DECIMAL_POINT;
    }
}

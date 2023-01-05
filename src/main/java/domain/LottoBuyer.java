package domain;

import dto.LottoResult;
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
    
    public List<Lotto> buyFrom(LottoPurchasePlace lottoPurchasePlace, List<Lotto> manualLottos) {
        List<Lotto> purchasedLottos = lottoPurchasePlace.purchase(money, manualLottos);
        this.lottos.addAll(purchasedLottos);
        return this.lottos;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> statuses = lottos.stream()
                .map(lotto -> lotto.getRank(winningLotto))
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

        if (money == 0) {
            return 0.00d;
        }
        Integer investMoney = (money / LOTTO_COST) * LOTTO_COST;

        if ((earningMoney - investMoney) == 0) {
            return 0.00d;
        }
        Double rate = (earningMoney - investMoney) / (double) investMoney * 100;
        return Math.floor(rate * RATE_DECIMAL_POINT) / RATE_DECIMAL_POINT;
    }
}

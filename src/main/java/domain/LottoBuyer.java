package domain;

import dto.LottoResult;
import dto.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoBuyer {
    private static final Integer LOTTO_COST = LottoStore.LOTTO_COST;
    private static final Integer RATE_DECIMAL_POINT = 100;

    private final LottoStore lottoStore;
    private Integer money;
    private List<Lotto> manualLottos = new ArrayList<>();
    private List<Lotto> autoLottos = new ArrayList<>();

    public LottoBuyer(Integer money, LottoStore lottoStore) {
        this.money = money;
        this.lottoStore = lottoStore;
    }

    public void buyManual(List<Lotto> manaulLottos) {
        lottoStore.validatePurchase(money, manaulLottos.size());

        this.manualLottos = new ArrayList<>(manaulLottos);
        money -= manaulLottos.size() * LOTTO_COST;
    }

    public void buyAuto() {
        Integer amount = lottoStore.getPurchaseAmount(money);

        this.autoLottos = IntStream.range(0, amount)
                .mapToObj((i) -> Lotto.ofAuto())
                .collect(Collectors.toList());
        money -= autoLottos.size() * LOTTO_COST;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<Lotto> lottos = Stream.concat(manualLottos.stream(), autoLottos.stream())
                .collect(Collectors.toList());
        List<LottoRank> statuses = lottos.stream()
                .map(winningLotto::getLottoRank)
                .collect(Collectors.toList());

        Map<LottoRank, Integer> map = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            map.put(lottoRank, Collections.frequency(statuses, lottoRank));
        }

        Double earningRate =  calculateEarningRate(map);
        return new LottoResult(map, earningRate);
    }

    private Double calculateEarningRate(Map<LottoRank, Integer> map) {
        Long earningMoney = map.entrySet()
            .stream()
            .mapToLong(entry -> entry.getValue() * entry.getKey().getReward())
            .reduce(0, Long::sum);

        Integer investMoney = (autoLottos.size() + manualLottos.size()) * LOTTO_COST;
        Double rate = ((earningMoney - investMoney) / (double) investMoney) * 100;
        if (investMoney == 0) {
            return 0.00d;
        }
        return Math.floor(rate * RATE_DECIMAL_POINT) / RATE_DECIMAL_POINT;
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }

    public List<Lotto> getAutoLottos() {
        return autoLottos;
    }
}

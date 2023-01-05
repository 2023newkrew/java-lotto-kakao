package domain;

import dto.LottoResult;
import dto.WinningLotto;
import java.util.*;
import java.util.stream.Collectors;

import static utils.ErrorMessage.NOT_ENOUGH_MONEY;

public class LottoBuyer {
    private Integer money;
    private List<Lotto> lottos;

    public LottoBuyer(Integer money) {
        this.money = money;
        this.lottos = new ArrayList<>();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void buyLottos(Integer manualAmount, Integer automaticAmount) {
        buyManualLottos(manualAmount);
        buyAutomaticLottos(automaticAmount);
    }

    public void buyManualLottos(Integer amount) {
        validateMoney(money);
        LottoStore lottoStore = new ManualStore();
        lottos.addAll(lottoStore.buy(amount));
        payLottoMoney(amount);
    }

    public void buyAutomaticLottos(Integer amount) {
        validateMoney(money);
        LottoStore lottoStore = new AutomaticStore();
        lottos.addAll(lottoStore.buy(amount));
        payLottoMoney(amount);
    }

    private void payLottoMoney(Integer amount) {
        money -= (amount * LottoStore.COST);
    }

    private void validateMoney(Integer money) {
        if (money < LottoStore.COST) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY.getMessage());
        }
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<Rank> statuses = lottos.stream()
                .map(lotto -> lotto.getRank(winningLotto))
                .collect(Collectors.toList());
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            map.put(rank, Collections.frequency(statuses, rank));
        }
        Double earningRate =  calculateEarningRate(map);
        return new LottoResult(map, earningRate);
    }

    public Double calculateEarningRate(Map<Rank, Integer> map) {
        Integer investMoney = lottos.size() * LottoStore.COST;
        Long earningMoney = 0L;
        for (Rank rank : Rank.values()) {
            earningMoney +=  (map.get(rank) * rank.getReward());
        }
        if (earningMoney == 0L) {
            return 0.00d;
        }
        return Math.floor(earningMoney / (double) investMoney * 100) / 100;
    }

}

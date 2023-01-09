package lotto.model;

import lotto.model.strategy.LottoIssueStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {

    private final LottoIssueStrategy strategy;

    public LottoIssuer(LottoIssueStrategy strategy) {
        this.strategy = strategy;
    }

    public LottoList issue(Integer count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(strategy.issue());
        }
        return new LottoList(lottoList);
    }
}

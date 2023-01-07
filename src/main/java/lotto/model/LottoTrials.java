package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<Lotto> lottoTrials = new ArrayList<>();

    public void addLotto(Lotto lotto){
        lottoTrials.add(lotto);
    }
    
    public Lotto getLottoTrial(int index){
        return lottoTrials.get(index);
    }
}

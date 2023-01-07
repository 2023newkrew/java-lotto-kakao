package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<Lotto> lottos = new ArrayList<>();

    public void add(Lotto lotto){
        lottos.add(lotto);
    }
    
    public Lotto get(int index){
        return lottos.get(index);
    }
}

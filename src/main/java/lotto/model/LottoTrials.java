package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<LottoTrial> lottoTrials = new ArrayList<>();

    public void add(LottoTrial lottoTrial){
        lottoTrials.add(lottoTrial);
    }
    
    public LottoTrial get(int index){
        return lottoTrials.get(index);
    }
}

package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<LottoTrial> lottoTrials = new ArrayList<>();

    public void add(LottoTrial lottoTrial){
        lottoTrials.add(lottoTrial);
    }

    public int size(){
        return lottoTrials.size();
    }

    public LottoTrial get(int index){
        return lottoTrials.get(index);
    }
}

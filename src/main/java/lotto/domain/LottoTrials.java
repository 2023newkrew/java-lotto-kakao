package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LottoTrials implements Iterator<LottoTrial> {
    private final List<LottoTrial> lottoTrials = new ArrayList<>();
    private Iterator<LottoTrial> lottoTrialIterator = lottoTrials.iterator();
    public void add(LottoTrial lottoTrial){
        lottoTrials.add(lottoTrial);
    }

    public int size(){
        return lottoTrials.size();
    }
    public LottoTrial get(int index){
        return lottoTrials.get(index);
    }

    @Override
    public boolean hasNext() {
        return lottoTrialIterator.hasNext();
    }

    @Override
    public LottoTrial next() {
        return lottoTrialIterator.next();
    }
}

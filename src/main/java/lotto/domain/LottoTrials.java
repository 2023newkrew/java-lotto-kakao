package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * LottoResults can contain one or more LottoTrials.
 * You can add LottoTrial instance manually.
 * Iterator is provided for searching individual LottoTrial instance.
 */
public class LottoTrials implements Iterator<LottoTrial> {
    private final List<LottoTrial> lottoTrials = new ArrayList<>();
    private Iterator<LottoTrial> lottoTrialIterator = lottoTrials.iterator();
    public void add(LottoTrial lottoTrial){
        lottoTrials.add(lottoTrial);
        lottoTrialIterator = lottoTrials.iterator();
    }

    public void addAll(LottoTrials lottoTrials){
        this.lottoTrials.addAll(lottoTrials.lottoTrials);
        lottoTrialIterator = this.lottoTrials.iterator();
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

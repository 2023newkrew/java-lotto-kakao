package lotto.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LottoResults implements Iterator<LottoResult> {
    private final Set<LottoResult> lottoResultSet = new HashSet<>();
    private String groupString;
    private Iterator<LottoResult> iterator = lottoResultSet.iterator();
    public LottoResults add(LottoResult lottoResult){
        lottoResultSet.add(lottoResult);
        return this;
    }
    public LottoResults setGroupString(String groupString){
        this.groupString=groupString;
        return this;
    }

    public boolean contains(LottoResult lottoResult){
        return lottoResultSet.contains(lottoResult);
    }

    @Override
    public String toString() {
        return groupString;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public LottoResult next() {
        return iterator.next();
    }

    public Iterator<LottoResult> getIterator() {
        return lottoResultSet.iterator();
    }
}

package lotto.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * LottoResults can contain one or more LottoResults.
 * You can add LottoResult instance manually.
 * Iterator is provided for searching individual lottoResult instance.
 * You can set GroupString if representative String is needed.
 */
public class LottoResults implements Iterator<LottoResult> {
    private final Set<LottoResult> lottoResultSet = new HashSet<>();
    private String groupString;
    private Iterator<LottoResult> iterator = lottoResultSet.iterator();
    public LottoResults add(LottoResult lottoResult){
        lottoResultSet.add(lottoResult);
        return this;
    }

    /**
     * Set GroupString, which represents name of group.
     * @param groupString to set what to return at toString() method.
     * @return this to provide function chaining.
     */
    public LottoResults setGroupString(String groupString){
        this.groupString=groupString;
        return this;
    }

    /**
     * very similar to contains method of Collections.
     * @return true if exists, else false.
     */
    public boolean contains(LottoResult lottoResult){
        return lottoResultSet.contains(lottoResult);
    }

    /**
     * @return GroupString, which represents name of group.
     */
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

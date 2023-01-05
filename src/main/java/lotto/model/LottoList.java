package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class LottoList {

    private final List<Lotto> lottoList;

    public LottoList(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public LottoList() {
        lottoList = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        this.lottoList.add(lotto);
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public Lotto get(Integer index) {
        return lottoList.get(index);
    }

    public LottoList union(LottoList other) {
        ArrayList<Lotto> extendedLottoList = new ArrayList<>(lottoList);
        extendedLottoList.addAll(other.lottoList);
        return new LottoList(extendedLottoList);
    }

    public Integer length() {
        return lottoList.size();
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        lottoList.forEach(lotto -> stringJoiner.add(lotto.toString()));
        return stringJoiner.toString();
    }
}

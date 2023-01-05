package lotto.model;

import java.util.*;
import java.util.stream.Collectors;

public class LottoList {

    private final List<Lotto> lottoList;

    public LottoList(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public LottoList(Lotto... lottoList) {
        this(Arrays.stream(lottoList)
                .collect(Collectors.toList()));
    }

    public Lotto get(Integer index) {
        return lottoList.get(index);
    }

    public LottoList merge(LottoList other) {
        ArrayList<Lotto> mergedLottoList = new ArrayList<>(lottoList);
        mergedLottoList.addAll(other.lottoList);
        return new LottoList(mergedLottoList);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoList)) return false;
        LottoList lottoList1 = (LottoList) o;
        if (lottoList.size() != lottoList1.lottoList.size()) return false;
        return new HashSet<>(lottoList).containsAll(lottoList1.lottoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoList);
    }
}

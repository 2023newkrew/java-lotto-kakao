package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    private List<LottoNumbers> lottoNumbersList;

    public Lotto(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = lottoNumbersList;
    }

    public Map<Rank, Integer> rankEachLotto(LottoNumbers winLottoNumbers, LottoNumber bonusBall) {
        return lottoNumbersList.stream()
                .map(lottoNumbers -> lottoNumbers.compareWithWinLottoNumbers(winLottoNumbers, bonusBall))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(value -> 1)));
    }

}

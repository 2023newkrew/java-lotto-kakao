package lotto.service;

import lotto.domain.LottoRandom;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    private final LottoRandom lottoRandom;

    public LottoStore(LottoRandom lottoRandom) {
        this.lottoRandom = lottoRandom;
    }

    public List<LottoTicket> storeLotto(int count){
        return IntStream.range(0, count)
                .mapToObj(i -> new LottoTicket(lottoRandom.createRandomNumbers()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(count)));
    }
}

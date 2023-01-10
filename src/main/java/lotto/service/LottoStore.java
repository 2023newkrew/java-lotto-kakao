package lotto.service;

import lotto.domain.LottoRandom;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoStore {

    private final LottoRandom lottoRandom;

    public LottoStore(LottoRandom lottoRandom) {
        this.lottoRandom = lottoRandom;
    }

    public List<LottoTicket> storeAutoLotto(int count){
        return IntStream.range(0, count)
                .mapToObj(i -> new LottoTicket(lottoRandom.createRandomNumbers()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(count)));
    }

    public List<LottoTicket> storeManualLotto(List<String> manual){
        return manual.stream()
                .map(userInput -> changeToLottoNumber(userInput))
                .collect(Collectors.toList());
    }

    private LottoTicket changeToLottoNumber(String userInput){
        Integer[] numbers = stringToArray(userInput);
        return new LottoTicket(Arrays.asList(numbers));
    }

    private Integer[] stringToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}

package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.utils.Constants.*;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(){
        this.lottoNumbers = createRandomNumbers();
    }

    public LottoTicket(ArrayList<LottoNumber> lottoNumbers) {
        lottoNumberCountCheck(lottoNumbers);
        lottoNumberDuplicateCheck(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    private ArrayList<LottoNumber> createRandomNumbers(){
        List<Integer> numList = IntStream.range(1, LOTTO_UPPER_BOUND + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numList);
        numList = numList.subList(0, LOTTO_TICKET_SIZE);
        Collections.sort(numList);
        return numList.stream().map(LottoNumber::new).collect(Collectors.toCollection(ArrayList::new));
    }

    private void lottoNumberCountCheck(ArrayList<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 6개가 아닙니다");
        }
    }

    private void lottoNumberDuplicateCheck(ArrayList<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .distinct().count() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호에 중복이 존재합니다.");
        }
    }
}
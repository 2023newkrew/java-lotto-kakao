package lotto.service;

import lotto.model.LottoNumber;
import lotto.model.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.config.LottoConfig.*;

public class LottoMachine {
    public LottoTicket createRandomLottoTicket(){
        return new LottoTicket(createRandomLottoNumbers());
    }

    private List<LottoNumber> createRandomLottoNumbers(){
        List<LottoNumber> list = new ArrayList<>();
        for(int number = LOTTO_NUMBER_LOWER_BOUNDARY; number <= LOTTO_NUMBER_UPPER_BOUNDARY; number++){
            list.add(new LottoNumber(number));
        }

        Collections.shuffle(list);
        list = list.subList(0, LOTTO_TICKET_LENGTH);
        Collections.sort(list);

        return list;
    }

    public LottoTicket createManualLottoTicket(List<Integer> inputLottoNumbers){
        List<LottoNumber> lottoNumbers =
                inputLottoNumbers.stream()
                        .map(LottoNumber::new)
                        .sorted()
                        .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }
}

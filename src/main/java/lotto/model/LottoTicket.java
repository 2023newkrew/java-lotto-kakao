package lotto.model;


import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class LottoTicket {
    private static final Integer LOTTO_TICKET_LENGTH = 6;
    private static final Integer LOTTO_NUMBER_LOWER_BOUNDARY = 1;
    private static final Integer LOTTO_NUMBER_UPPER_BOUNDARY = 45;

    private final List<Integer> lotto;

    public LottoTicket(){
        lotto = createRandomList();
    }
    public LottoTicket(List<Integer> input){
        validateLottoNumber(input);
        lotto = input;
    }
    private List<Integer> createRandomList(){
        List<Integer> list = new ArrayList<>();
        for(int i = LOTTO_NUMBER_LOWER_BOUNDARY; i <= LOTTO_NUMBER_UPPER_BOUNDARY; i++){
            list.add(i);
        }

        Collections.shuffle(list);
        return list.subList(0, 6);
    }

    private void validateLottoNumber(List<Integer> input){
        if(input == null || input.size() != LOTTO_TICKET_LENGTH){
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_LENGTH);
        }

        input.forEach(number -> {
            if(number < LOTTO_NUMBER_LOWER_BOUNDARY || number > LOTTO_NUMBER_UPPER_BOUNDARY){
                throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE);
            }
        });
    }

    public boolean contains(Integer number) {
        return lotto.contains(number);
    }

    public Integer countIncludedNumber(LottoTicket lottoTicket) {
        return Math.toIntExact(lotto.stream()
                .filter(lottoTicket::contains)
                .count());
    }
}

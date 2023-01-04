package lotto.model;


import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final Integer LOTTO_TICKET_LENGTH = 6;
    private static final Integer LOTTO_NUMBER_LOWER_BOUNDARY = 1;
    private static final Integer LOTTO_NUMBER_UPPER_BOUNDARY = 45;

    private final List<LottoNumber> lotto;

    public LottoTicket(){
        lotto = createRandomLottoNumbers();
        Collections.sort(lotto);
    }

    private List<LottoNumber> createRandomLottoNumbers(){
        List<LottoNumber> list = new ArrayList<>();
        for(int number = LOTTO_NUMBER_LOWER_BOUNDARY; number <= LOTTO_NUMBER_UPPER_BOUNDARY; number++){
            list.add(new LottoNumber(number));
        }

        Collections.shuffle(list);
        return list.subList(0, LOTTO_TICKET_LENGTH);
    }

    public LottoTicket(List<LottoNumber> input){
        validateLottoTicketLength(input);
        lotto = input;
    }

    private void validateLottoTicketLength(List<LottoNumber> input){
        if(input == null || input.size() != LOTTO_TICKET_LENGTH){
            throw new LottoException(ErrorCode.INVALID_LOTTO_NUMBER_LENGTH);
        }
    }

    public LottoRank checkLottoRank(LottoWinningNumber lottoWinningNumber){
        Integer sameCount = countOverlappingNumber(lottoWinningNumber.getWinningNumber());
        boolean isBonus = lotto.contains(lottoWinningNumber.getBonusBall());

        return LottoRank.fromCountAndBonus(sameCount, isBonus);
    }

    public Integer countOverlappingNumber(LottoTicket lottoTicket) {
        return Math.toIntExact(
                lotto.stream()
                .filter(lottoTicket::contains)
                .count()
        );
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
    }

    @Override
    public String toString() {
        return "[" +
                lotto.stream()
                .map(LottoNumber::getLottoNumberString)
                .collect(Collectors.joining(", ")) +
                "]";
    }
}

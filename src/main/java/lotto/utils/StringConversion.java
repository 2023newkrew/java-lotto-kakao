package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StringConversion {

    public LottoTicket changeToLottoTicket(String userNumbers){
        LottoNumber[] numbers = convertToLottoArray(userNumbers);
        return new LottoTicket(new ArrayList<>(List.of(numbers)));
    }

    public LottoWinnerTicket changeToWinnerTicket(String userNumbers, LottoNumber bonus){
        LottoNumber[] numbers = convertToLottoArray(userNumbers);
        return new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(numbers))), bonus);
    }

    public LottoNumber[] convertToLottoArray(String userInput){
        try{
            return Stream.of(userInput
                            .replace(" ", "")
                            .split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .map(LottoNumber::new)
                    .toArray(LottoNumber[]::new);
        } catch (Exception e){
            throw new IllegalArgumentException("입력 값에서 로또 범위를 벗어나는 수가 존재합니다");
        }
    }
}

package lotto.utils;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StringConversion {

    public LottoWinnerTicket changeToWinnerTicket(String userNumbers, int bonus){
        Integer[] numbers = convertToArray(userNumbers);
        return new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(numbers))), bonus);
    }

    public Integer[] convertToArray(String userInput){
        return Stream.of(userInput
                        .replace(" ", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
    }
}

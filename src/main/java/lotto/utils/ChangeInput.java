package lotto.utils;

import lotto.domain.LottoTicket;
import lotto.domain.LottoWinnerTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChangeInput {

    public LottoWinnerTicket changeToWinnerTicket(String userInput, int bonus){
        Integer[] numbers = stringToArray(userInput);
        return new LottoWinnerTicket(
                new LottoTicket(new ArrayList<>(List.of(numbers))), bonus);
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

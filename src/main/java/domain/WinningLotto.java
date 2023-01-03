package domain;

import util.validator.WinningLottoValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto extends Lotto{
    private final List<Integer> winningLotto;
    public WinningLotto(String input){
        WinningLottoValidator.validate(input);
        String[] splitInput = input.split(", ");
        this.winningLotto = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    
}

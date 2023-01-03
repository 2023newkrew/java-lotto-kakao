package domain;

import common.constant.Constants;
import util.validator.WinningLottoValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto extends Lotto {
    private final List<Integer> winningLottoNumbers;

    public WinningLotto(String input) {
        WinningLottoValidator.validate(input);
        String[] splitInput = input.split(Constants.DELIMITER);
        this.winningLottoNumbers = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getWinningLottoNumbers(){
        return new ArrayList<>(winningLottoNumbers);
    }

}

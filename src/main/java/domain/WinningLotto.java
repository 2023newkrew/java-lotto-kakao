package domain;

import common.constant.Constants;
import util.validator.WinningLottoValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private final List<LottoNumber> winningLottoNumbers;

    public WinningLotto(String input) {
        WinningLottoValidator.validate(input);
        String[] splitInput = input.split(Constants.DELIMITER);
        winningLottoNumbers = Arrays.stream(splitInput)
                .map(inputString -> new LottoNumber(inputString))
                .collect(Collectors.toList());
    }

    public List<LottoNumber> getWinningLottoNumbers(){
        return new ArrayList<>(winningLottoNumbers);
    }

}

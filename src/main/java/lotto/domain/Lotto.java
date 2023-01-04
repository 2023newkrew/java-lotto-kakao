package lotto.domain;

import static lotto.domain.LottoNumbers.makeLottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public static Lotto makeLottoAuto(GeneratePolicy generatePolicy){
        List<Integer> numbers = new ArrayList<>();
        while(numbers.size() != 6){
            addGeneratedNumber(numbers, generatePolicy.generate());
        }
        return makeLotto(numbers);
    }

    private static void addGeneratedNumber(List<Integer> numbers, int generate) {
        if(!numbers.contains(generate)){
            numbers.add(generate);
        }
    }

    public static Lotto makeLotto(List<Integer> numbers){
        return new Lotto(numbers);
    }


    private Lotto(List<Integer> numbers) {
        this.lottoNumbers = makeLottoNumbers(numbers);
    }

    public String lottoToString(){
        return lottoNumbers.toString();
    }

}

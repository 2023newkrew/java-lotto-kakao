import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    private static final Integer LOTTO_SIZE = 6;
    private static final Integer LOTTO_NUMBER_START = 1;
    private static final Integer LOTTO_NUMBER_END = 45;

    public List<LottoNumber> run() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            numbers.add(chooseUniqueLottoNumber(numbers));
        }
        return numbers;
    }

    private LottoNumber chooseUniqueLottoNumber(List<LottoNumber> numbers) {
        LottoNumber lottoNumber = new LottoNumber(new Random().nextInt(LOTTO_NUMBER_END) + LOTTO_NUMBER_START);
        while (numbers.contains(lottoNumber)) {
            lottoNumber = new LottoNumber(new Random().nextInt(LOTTO_NUMBER_END) + LOTTO_NUMBER_START);
        }
        return lottoNumber;
    }

}

package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int START_INDEX = 0;
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    public static final int LENGTH = 6;
    private static final List<LottoNumber> WHOLE_NUMBERS = IntStream.rangeClosed(MINIMUM, MAXIMUM)
            .boxed().map(number -> new LottoNumber(number))
            .collect(Collectors.toList());
    private static AutoLottoGenerator autoLottoGenerator;

    private AutoLottoGenerator() {}

    public static AutoLottoGenerator get() {
        if (autoLottoGenerator == null) {
            autoLottoGenerator = new AutoLottoGenerator();
        }
        return autoLottoGenerator;
    }

    @Override
    public Lotto generateLotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<LottoNumber> numbers = new ArrayList<>(WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + LENGTH));
        return new Lotto(numbers.stream().sorted().collect(Collectors.toList()));
    }

}

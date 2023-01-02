import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    Lotto() {
        this.numbers = LottoGenerator.run();
    }
}

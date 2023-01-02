import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    @Test
    void 로또숫자의_갯수는_여섯개이다() {
        // given
        LottoGenerator lottoGenerator = new LottoGenerator();

        // when
        List<LottoNumber> lottoNumbers = lottoGenerator.run();

        // then
        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @Test
    void 로또숫자는_중복될_수_없다() {
        // given
        LottoGenerator lottoGenerator = new LottoGenerator();

        // when
        List<LottoNumber> lottoNumbers = lottoGenerator.run();

        // then
        List<LottoNumber> result = lottoNumbers
                .stream()
                .distinct()
                .collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(6);
    }
}

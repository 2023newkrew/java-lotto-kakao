package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.LottoGradeEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import lotto.dto.LottoGradeCountResult;

import org.junit.jupiter.params.provider.MethodSource;

class LottoGradeCounterTest {

    private LottoGradeCounter lottoGradeCounter;

    @BeforeEach
    void setUp() {
        lottoGradeCounter = new LottoGradeCounter();
    }

    @AfterEach
    void tearDown() {
        lottoGradeCounter.clear();
    }

    @ParameterizedTest
    @MethodSource("gradeArgument")
    void 생성될_때_1등부터_5등까지의_키값을_설정한다(LottoGradeEnum grade, boolean expected) {
        assertThat(lottoGradeCounter.getLottoGradeCountResults()
                .stream()
                .map(LottoGradeCountResult::getGrade)
                .collect(Collectors.toSet())
                .contains(grade))
                .isEqualTo(expected);
    }

    static Stream<Arguments> gradeArgument() {
        return Stream.of(
                Arguments.arguments(LottoGradeEnum.FIRST, true),
                Arguments.arguments(LottoGradeEnum.SECOND, true),
                Arguments.arguments(LottoGradeEnum.THIRD, true),
                Arguments.arguments(LottoGradeEnum.FOURTH, true),
                Arguments.arguments(LottoGradeEnum.FIFTH, true),
                Arguments.arguments(LottoGradeEnum.NONE_GRADE, false)
        );
    }

}